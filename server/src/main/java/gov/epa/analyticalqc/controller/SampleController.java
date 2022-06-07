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

}