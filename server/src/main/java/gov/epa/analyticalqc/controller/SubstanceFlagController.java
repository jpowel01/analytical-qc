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

import gov.epa.analyticalqc.entity.SubstanceFlag;
import gov.epa.analyticalqc.repository.SubstanceFlagRepository;

@RestController
@RequestMapping("/api/substance-flags")
public class SubstanceFlagController {
    
    @Autowired SubstanceFlagRepository substanceFlagRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceFlag> getSubstanceFlag(@PathVariable("id") Integer id) {
        Optional<SubstanceFlag> findSubstanceFlag = substanceFlagRepository.findById(id);
        if (findSubstanceFlag.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance flag not found");
        } else {
            return new ResponseEntity<>(findSubstanceFlag.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceFlag> updateSubstanceFlag(@PathVariable("id") Integer id, @RequestBody SubstanceFlag substanceFlag) {
        Optional<SubstanceFlag> findSubstanceFlag = substanceFlagRepository.findById(id);
        if (findSubstanceFlag.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance flag not found");
        } else {
            return new ResponseEntity<>(substanceFlagRepository.save(substanceFlag), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SubstanceFlag> createSubstanceFlag(@RequestBody SubstanceFlag substanceFlag) {
        try {
			SubstanceFlag _substanceFlag = substanceFlagRepository.save(substanceFlag);
			return new ResponseEntity<>(_substanceFlag, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating substance flag");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSubstanceFlag(@PathVariable("id") Integer id) {
		try {
			substanceFlagRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting substance flag");
		}
	}

}