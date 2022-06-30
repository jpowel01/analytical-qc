package gov.epa.analyticalqc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.dto.CreateListRequest;
import gov.epa.analyticalqc.dto.ListDetail;
import gov.epa.analyticalqc.entity.ListSubstance;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.repository.ListRepository;
import gov.epa.analyticalqc.repository.ListSubstanceRepository;
import gov.epa.analyticalqc.repository.SubstanceAnnotationRepository;
import gov.epa.analyticalqc.repository.SubstanceCallRepository;
import gov.epa.analyticalqc.repository.SubstanceGradeRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/lists")
public class ListController {
    
    @Autowired ListRepository listRepository;
    @Autowired ListSubstanceRepository listSubstanceRepository;
    @Autowired SubstanceRepository substanceRepository;
    @Autowired SubstanceGradeRepository substanceGradeRepository;
    @Autowired SubstanceCallRepository substanceCallRepository;
    @Autowired SubstanceAnnotationRepository substanceAnnotationRepository;

    @GetMapping()
    public ResponseEntity<List<gov.epa.analyticalqc.entity.List>> getAllLists() {
        return new ResponseEntity<>(listRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ListDetail> getListDetail(@PathVariable("id") Integer id) {
        gov.epa.analyticalqc.entity.List list = null;
        try {
            list = listRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        }
        
        List<Integer> substanceIds = listSubstanceRepository.findSubstanceIdsByListId(id);
        return new ResponseEntity<>(new ListDetail(list, substanceIds), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<gov.epa.analyticalqc.entity.List> createList(@RequestBody CreateListRequest request) {
        List<Substance> substances = new ArrayList<Substance>();
        String createBy = request.getCreateBy();
        if (createBy.equals("mw")) {
            Double min = request.getMwMin();
            Double max = request.getMwMax();
            if (min != null && max != null) {
                substances = substanceRepository.findByMolWeightBetween(min, max);
            } else if (min != null) {
                substances = substanceRepository.findByMolWeightGreaterThan(min);
            } else if (max != null) {
                substances = substanceRepository.findByMolWeightLessThan(max);
            }
        } else if (createBy.equals("dtxsid")) {
            String dtxsidsCsv = request.getDtxsids().replaceAll("[^DTXSID0-9]",",");
            List<String> dtxsidsList = Arrays.asList(dtxsidsCsv.split(","));
            substances = substanceRepository.findByDtxsidIn(dtxsidsList);
        } else if (createBy.equals("annotation")) {
            List<Integer> t0Grades = request.getT0Grades();
            Set<Integer> t0Ids = new HashSet<Integer>();
            t0Ids.addAll(substanceGradeRepository.findSubstanceIdsByGradeIdIn(t0Grades, false));
            if (t0Grades.contains(0)) {
                t0Ids.addAll(substanceRepository.findUngradedIds(false));
            }

            List<Integer> t4Grades = request.getT4Grades();
            Set<Integer> t4Ids = new HashSet<Integer>();
            t4Ids.addAll(substanceGradeRepository.findSubstanceIdsByGradeIdIn(t4Grades, true));
            if (t4Grades.contains(0)) {
                t4Ids.addAll(substanceRepository.findUngradedIds(true));
            }

            List<Integer> calls = request.getCalls();
            Set<Integer> callIds = new HashSet<Integer>();
            callIds.addAll(substanceCallRepository.findSubstanceIdsByCallIdIn(calls));
            if (calls.contains(0)) {
                callIds.addAll(substanceRepository.findUncalledIds());
            }

            Set<Integer> textIds = new HashSet<Integer>();
            if (request.getHasText()) {
                textIds.addAll(substanceAnnotationRepository.findAnnotatedSubstanceIds());
            }

            if (request.getHasNoText()) {
                textIds.addAll(substanceRepository.findUnannotatedIds());
            }

            t0Ids.retainAll(t4Ids);
            t0Ids.retainAll(callIds);
            t0Ids.retainAll(textIds);
            substances = substanceRepository.findByIdIn(new ArrayList<>(t0Ids));
        }

        if (substances.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty list");
        }

        String listName = request.getName();
        gov.epa.analyticalqc.entity.List list = listRepository.findByName(listName).orElse(null);
        Set<Integer> existingSubstanceIds = new HashSet<Integer>();
        if (list == null) {
            gov.epa.analyticalqc.entity.List saveList = new gov.epa.analyticalqc.entity.List(null, listName, request.getDescription());
            list = listRepository.save(saveList);
        } else {
            String listDescription = request.getDescription();
            if (listDescription != null && !listDescription.isBlank()) {
                list.setDescription(request.getDescription());
                list = listRepository.save(list);
            }
            
            existingSubstanceIds.addAll(listSubstanceRepository.findSubstanceIdsByListId(list.getId()));
        }

        List<ListSubstance> listSubstances = new ArrayList<ListSubstance>();
        for (Substance s:substances) {
            if (!existingSubstanceIds.contains(s.getId())) {
                listSubstances.add(new ListSubstance(null, s, list));
            }
        }
        listSubstanceRepository.saveAll(listSubstances);
    
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}