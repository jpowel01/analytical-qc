package gov.epa.analyticalqc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.entity.SampleCall;
import gov.epa.analyticalqc.repository.SampleCallRepository;

@RestController
@RequestMapping("/api/sample-calls")
public class SampleCallController {
    
    @Autowired SampleCallRepository sampleCallRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SampleCall> getSampleCall(@PathVariable("id") Integer id) {
        Optional<SampleCall> findSampleCall = sampleCallRepository.findById(id);
        if (findSampleCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample call not found");
        } else {
            return new ResponseEntity<>(findSampleCall.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleCall> updateSampleCall(@PathVariable("id") Integer id, @RequestBody SampleCall sampleCall) {
        Optional<SampleCall> findSampleCall = sampleCallRepository.findById(id);
        if (findSampleCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample call not found");
        } else {
            return new ResponseEntity<>(sampleCallRepository.save(sampleCall), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SampleCall> createSampleCall(@RequestBody SampleCall sampleCall) {
        try {
			SampleCall _sampleCall = sampleCallRepository.save(sampleCall);
			return new ResponseEntity<>(_sampleCall, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sample call");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSampleCall(@PathVariable("id") Integer id) {
		try {
			sampleCallRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting sample call");
		}
	}

}