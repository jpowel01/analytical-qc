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

import gov.epa.analyticalqc.entity.SubstanceCall;
import gov.epa.analyticalqc.repository.SubstanceCallRepository;

@RestController
@RequestMapping("/api/substance-calls")
public class SubstanceCallController {
    
    @Autowired SubstanceCallRepository substanceCallRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceCall> getSubstanceCall(@PathVariable("id") Integer id) {
        Optional<SubstanceCall> findSubstanceCall = substanceCallRepository.findById(id);
        if (findSubstanceCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance call not found");
        } else {
            return new ResponseEntity<>(findSubstanceCall.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<SubstanceCall> getSubstanceCallBySubstanceId(@PathVariable("substance-id") Integer id) {
        Optional<SubstanceCall> findSubstanceCall = substanceCallRepository.findBySubstanceId(id);
        if (findSubstanceCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance call not found");
        } else {
            return new ResponseEntity<>(findSubstanceCall.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceCall> updateSubstanceCall(@PathVariable("id") Integer id, @RequestBody SubstanceCall substanceCall) {
        Optional<SubstanceCall> findSubstanceCall = substanceCallRepository.findById(id);
        if (findSubstanceCall.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance call not found");
        } else {
            return new ResponseEntity<>(substanceCallRepository.save(substanceCall), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SubstanceCall> createSubstanceCall(@RequestBody SubstanceCall substanceCall) {
        try {
			SubstanceCall _substanceCall = substanceCallRepository.save(substanceCall);
			return new ResponseEntity<>(_substanceCall, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating substance call");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSubstanceCall(@PathVariable("id") Integer id) {
		try {
			substanceCallRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting substance call");
		}
	}

}