package gov.epa.analyticalqc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.dto.ExperimentDetail;
import gov.epa.analyticalqc.dto.ExperimentDto;
import gov.epa.analyticalqc.dto.PropertyPredictionDto;
import gov.epa.analyticalqc.dto.SampleDetail;
import gov.epa.analyticalqc.dto.SampleDto;
import gov.epa.analyticalqc.dto.SubstanceDetail;
import gov.epa.analyticalqc.entity.Grade;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceCall;
import gov.epa.analyticalqc.entity.SubstanceFlag;
import gov.epa.analyticalqc.entity.SubstanceGrade;
import gov.epa.analyticalqc.repository.ExperimentGradeRepository;
import gov.epa.analyticalqc.repository.ExperimentRepository;
import gov.epa.analyticalqc.repository.PropertyPredictionRepository;
import gov.epa.analyticalqc.repository.SampleRepository;
import gov.epa.analyticalqc.repository.SubstanceCallRepository;
import gov.epa.analyticalqc.repository.SubstanceFlagRepository;
import gov.epa.analyticalqc.repository.SubstanceGradeRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/substances")
public class SubstanceController {
    
    @Autowired SubstanceRepository substanceRepository;
    @Autowired SampleRepository sampleRepository;
    @Autowired SubstanceFlagRepository substanceFlagRepository;
    @Autowired PropertyPredictionRepository propertyPredictionRepository;
    @Autowired ExperimentRepository experimentRepository;
    @Autowired ExperimentGradeRepository experimentGradeRepository;
    @Autowired SubstanceGradeRepository substanceGradeRepository;
    @Autowired SubstanceCallRepository substanceCallRepository;

    @GetMapping()
    public ResponseEntity<Page<Substance>> getAllSubstances(@RequestParam(name="search", required=false) String search,
        @RequestParam(name="pageNo", defaultValue="0") Integer pageNo, 
        @RequestParam(name="pageSize", defaultValue="100") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        if (search != null) {
            return new ResponseEntity<>(substanceRepository.findByPreferredNameContainingIgnoreCase(search, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(substanceRepository.findAll(pageable), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Substance> getSubstance(@PathVariable("id") Integer id) {
        Optional<Substance> findSubstance = substanceRepository.findById(id);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            return new ResponseEntity<>(findSubstance.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<SubstanceDetail> getSubstanceDetail(@PathVariable("id") Integer id) {
        Optional<Substance> findSubstance = substanceRepository.findById(id);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            Substance substance = findSubstance.get();
            PropertyPredictionDto propertyPrediction = propertyPredictionRepository.findDtoBySubstanceId(id).orElse(null);
            List<SampleDto> samples = sampleRepository.findDtoBySubstanceId(id);

            List<SampleDetail> sampleDetails = new ArrayList<SampleDetail>();
            for (SampleDto sample:samples) {
                List<ExperimentDto> experiments = experimentRepository.findDtoBySampleId(sample.getId());
                List<ExperimentDetail> experimentDetails = new ArrayList<ExperimentDetail>();
                for (ExperimentDto experiment:experiments) {
                    List<Grade> grades = experimentGradeRepository.findByExperimentId(experiment.getId()).stream().map(eg -> eg.getGrade()).collect(Collectors.toList());
                    experimentDetails.add(new ExperimentDetail(experiment, grades));
                }

                sampleDetails.add(new SampleDetail(sample, experimentDetails));
            }

            SubstanceDetail substanceDetail = new SubstanceDetail(substance, propertyPrediction, sampleDetails);
            return new ResponseEntity<>(substanceDetail, HttpStatus.OK);
        }
    }

    @GetMapping("/dtxsid/{dtxsid}")
    public ResponseEntity<Substance> getSubstanceByDtxsid(@PathVariable("dtxsid") String dtxsid) {
        Optional<Substance> findSubstance = substanceRepository.findByDtxsid(dtxsid);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            return new ResponseEntity<>(findSubstance.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/casrn/{casrn}")
    public ResponseEntity<Substance> getSubstanceByCasrn(@PathVariable("casrn") String casrn) {
        Optional<Substance> findSubstance = substanceRepository.findByCasrn(casrn);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            return new ResponseEntity<>(findSubstance.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/flags")
    public ResponseEntity<List<SubstanceFlag>> getSubstanceFlagsBySubstanceId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(substanceFlagRepository.findBySubstanceId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<SubstanceGrade>> getSubstanceGradesBySubstanceId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(substanceGradeRepository.findBySubstanceId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/call")
    public ResponseEntity<SubstanceCall> getSubstanceCallBySubstanceId(@PathVariable("id") Integer id) {
        Optional<SubstanceCall> findSubstanceCall = substanceCallRepository.findBySubstanceId(id);
        if (findSubstanceCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance call not found");
        } else {
            return new ResponseEntity<>(findSubstanceCall.get(), HttpStatus.OK);
        }
    }

}