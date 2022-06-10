package gov.epa.analyticalqc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.dto.ExperimentDto;
import gov.epa.analyticalqc.dto.PropertyPredictionDto;
import gov.epa.analyticalqc.dto.SampleDetail;
import gov.epa.analyticalqc.dto.SampleDto;
import gov.epa.analyticalqc.dto.SubstanceDetail;
import gov.epa.analyticalqc.dto.SubstanceFlagDto;
import gov.epa.analyticalqc.entity.Experiment;
import gov.epa.analyticalqc.entity.PropertyPrediction;
import gov.epa.analyticalqc.entity.Sample;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceFlag;
import gov.epa.analyticalqc.repository.ExperimentRepository;
import gov.epa.analyticalqc.repository.PropertyPredictionRepository;
import gov.epa.analyticalqc.repository.SampleRepository;
import gov.epa.analyticalqc.repository.SubstanceFlagRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/substances")
@CrossOrigin(origins = "http://v2626umcth819.rtord.epa.gov:81")
public class SubstanceController {
    
    @Autowired SubstanceRepository substanceRepository;
    @Autowired SampleRepository sampleRepository;
    @Autowired SubstanceFlagRepository substanceFlagRepository;
    @Autowired PropertyPredictionRepository propertyPredictionRepository;
    @Autowired ExperimentRepository experimentRepository;

    @GetMapping()
    public ResponseEntity<List<Substance>> getAllSubstances() {
        return new ResponseEntity<>(substanceRepository.findAll(), HttpStatus.OK);
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
            PropertyPrediction propertyPrediction = propertyPredictionRepository.findBySubstanceId(id).orElse(null);
            List<SubstanceFlag> substanceFlags = substanceFlagRepository.findBySubstanceId(id);
            List<Sample> samples = sampleRepository.findBySubstanceId(id);

            PropertyPredictionDto propertyPredictionDto = null;
            if (propertyPrediction != null) {
                propertyPredictionDto = new PropertyPredictionDto(propertyPrediction);
            }

            List<SampleDetail> sampleDetails = new ArrayList<SampleDetail>();
            for (Sample sample:samples) {
                List<Experiment> experiments = experimentRepository.findBySampleId(sample.getId());
                sampleDetails.add(new SampleDetail(new SampleDto(sample), experiments.stream().map(e -> new ExperimentDto(e)).collect(Collectors.toList())));
            }

            SubstanceDetail substanceDetail = new SubstanceDetail(substance, 
                substanceFlags.stream().map(sf -> new SubstanceFlagDto(sf)).collect(Collectors.toList()), 
                propertyPredictionDto, 
                sampleDetails);
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

}