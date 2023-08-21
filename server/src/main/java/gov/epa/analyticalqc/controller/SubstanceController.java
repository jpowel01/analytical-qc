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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.dto.AmenabilityPredictionDto;
import gov.epa.analyticalqc.dto.ExperimentDetail;
import gov.epa.analyticalqc.dto.ExperimentDto;
import gov.epa.analyticalqc.dto.PropertyPredictionDto;
import gov.epa.analyticalqc.dto.SampleDetail;
import gov.epa.analyticalqc.dto.SampleDto;
import gov.epa.analyticalqc.dto.SubstanceDetail;
import gov.epa.analyticalqc.dto.SubstanceFileDto;
import gov.epa.analyticalqc.dto.SubstanceListRequest;
import gov.epa.analyticalqc.dto.SubstanceRow;
import gov.epa.analyticalqc.entity.Grade;
import gov.epa.analyticalqc.entity.Sample;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.repository.AmenabilityPredictionRepository;
import gov.epa.analyticalqc.repository.ExperimentGradeRepository;
import gov.epa.analyticalqc.repository.ExperimentRepository;
import gov.epa.analyticalqc.repository.PropertyPredictionRepository;
import gov.epa.analyticalqc.repository.SampleRepository;
import gov.epa.analyticalqc.repository.SubstanceFileRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/substances")
public class SubstanceController {
    
    @Autowired
    SubstanceRepository substanceRepository;
    @Autowired SampleRepository sampleRepository;
    @Autowired PropertyPredictionRepository propertyPredictionRepository;
    @Autowired AmenabilityPredictionRepository amenabilityPredictionRepository;
    @Autowired ExperimentRepository experimentRepository;
    @Autowired ExperimentGradeRepository experimentGradeRepository;
    @Autowired SubstanceFileRepository substanceFileRepository;

    @GetMapping()
    public ResponseEntity<Page<SubstanceRow>> getAllSubstances(@RequestParam(name="search", required=false) String search,
        @RequestParam(name="listId", required=false) Integer listId,
        @RequestParam(name="pageNo", defaultValue="0") Integer pageNo, 
        @RequestParam(name="pageSize", defaultValue="100") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        if (search != null && listId != null) {
            return new ResponseEntity<>(substanceRepository.findRowsByListIdAndPreferredNameContainingIgnoreCase(search, listId, pageable), HttpStatus.OK);
        } else if (search != null) {
            return new ResponseEntity<>(substanceRepository.findRowsByPreferredNameContainingIgnoreCase(search, pageable), HttpStatus.OK);
        } else if (listId != null) {
            return new ResponseEntity<>(substanceRepository.findRowsByListId(listId, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(substanceRepository.findAllRows(pageable), HttpStatus.OK);
        }
    }

    @GetMapping({"/{id}", "/id/{id}"})
    public ResponseEntity<Substance> getSubstance(@PathVariable("id") Integer id) {
        Optional<Substance> findSubstance = substanceRepository.findById(id);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            return new ResponseEntity<>(findSubstance.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/{type}/{query}/detail")
    public ResponseEntity<SubstanceDetail> getSubstanceDetail(@PathVariable("type") String type,
        @PathVariable("query") String query) {
        Substance substance = null;
        Sample sample = null;
        type = type.toLowerCase();

        switch (type) {
            case "id":
                Integer queryInt = Integer.parseInt(query.replaceAll("[^0-9]",""));
                substance = substanceRepository.findById(queryInt).orElse(null);
                break;
            case "cas":
            case "casrn":
                substance = substanceRepository.findByCasrn(query).orElse(null);
                break;
            case "dtxsid":
                substance = substanceRepository.findByDtxsid(query).orElse(null);
                break;
            case "tox21":
            case "tox21-id":
                Long queryLong = Long.parseLong(query.replaceAll("Tox21_",""));
                sample = sampleRepository.findByTox21Id(queryLong).orElse(null);
                break;
            case "ncgc":
            case "ncgc-id":
                sample = sampleRepository.findByNcgcId(query).orElse(null);
                break;
            case "barcode":
            case "bottle-barcode":
                sample = sampleRepository.findByBottleBarcode(query).orElse(null);
                break;
            default:
                substance = null;
                sample = null;
                break;
        }

        if (substance == null && sample != null) {
            substance = sample.getSubstance();
        }

        if (substance != null) {
            SubstanceDetail substanceDetail = buildSubstanceDetail(substance);
            return new ResponseEntity<>(substanceDetail, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        }
    }

    public SubstanceDetail buildSubstanceDetail(Substance substance) {
        Integer id = substance.getId();
        PropertyPredictionDto propertyPrediction = propertyPredictionRepository.findDtoBySubstanceId(id).orElse(null);
        AmenabilityPredictionDto amenabilityPrediction = amenabilityPredictionRepository.findDtoBySubstanceId(id).orElse(null);
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

        List<SubstanceFileDto> substanceFiles = substanceFileRepository.findDtoBySubstanceId(id);

        return new SubstanceDetail(substance, propertyPrediction, amenabilityPrediction, sampleDetails, substanceFiles);
    }

}