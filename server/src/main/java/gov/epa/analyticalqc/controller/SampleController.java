package gov.epa.analyticalqc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.entity.Sample;
import gov.epa.analyticalqc.repository.SampleRepository;

@RestController
@RequestMapping("/api/samples")
public class SampleController {
    
    @Autowired SampleRepository sampleRepository;

    @GetMapping()
    public ResponseEntity<List<Sample>> getAllSamples() {
        return new ResponseEntity<>(sampleRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sample> getSample(@PathVariable("id") Integer id) {
        Optional<Sample> findSample = sampleRepository.findById(id);
        if (findSample.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found");
        } else {
            return new ResponseEntity<>(findSample.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/ncgc-id/{ncgc-id}")
    public ResponseEntity<Sample> getSampleByNcgcId(@PathVariable("ncgc-id") String ncgcId) {
        Optional<Sample> findSample = sampleRepository.findByNcgcId(ncgcId);
        if (findSample.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found");
        } else {
            return new ResponseEntity<>(findSample.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/bottle-barcode/{bottle-barcode}")
    public ResponseEntity<Sample> getSampleByBottleBarcode(@PathVariable("bottle-barcode") String bottleBarcode) {
        Optional<Sample> findSample = sampleRepository.findByBottleBarcode(bottleBarcode);
        if (findSample.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found");
        } else {
            return new ResponseEntity<>(findSample.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/tox21-id/{tox21-id}")
    public ResponseEntity<Sample> getSampleByTox21Id(@PathVariable("tox21-id") Long tox21Id) {
        Optional<Sample> findSample = sampleRepository.findByTox21Id(tox21Id);
        if (findSample.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found");
        } else {
            return new ResponseEntity<>(findSample.get(), HttpStatus.OK);
        }
    }

}