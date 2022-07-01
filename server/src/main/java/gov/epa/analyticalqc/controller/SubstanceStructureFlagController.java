package gov.epa.analyticalqc.controller;

import java.util.List;
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

import gov.epa.analyticalqc.entity.SubstanceStructureFlag;
import gov.epa.analyticalqc.repository.SubstanceStructureFlagRepository;

@RestController
@RequestMapping("/api/substance-structure-flags")
public class SubstanceStructureFlagController {
    
    @Autowired SubstanceStructureFlagRepository substanceStructureFlagRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceStructureFlag> getSubstanceFlag(@PathVariable("id") Integer id) {
        Optional<SubstanceStructureFlag> findSubstanceFlag = substanceStructureFlagRepository.findById(id);
        if (findSubstanceFlag.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance flag not found");
        } else {
            return new ResponseEntity<>(findSubstanceFlag.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<List<SubstanceStructureFlag>> getSubstanceFlagsBySubstanceId(@PathVariable("substance-id") Integer id) {
        return new ResponseEntity<>(substanceStructureFlagRepository.findBySubstanceId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceStructureFlag> updateSubstanceFlag(@PathVariable("id") Integer id, @RequestBody SubstanceStructureFlag substanceFlag) {
        Optional<SubstanceStructureFlag> findSubstanceFlag = substanceStructureFlagRepository.findById(id);
        if (findSubstanceFlag.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance flag not found");
        } else {
            return new ResponseEntity<>(substanceStructureFlagRepository.save(substanceFlag), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SubstanceStructureFlag> createSubstanceFlag(@RequestBody SubstanceStructureFlag substanceFlag) {
        try {
			SubstanceStructureFlag _substanceFlag = substanceStructureFlagRepository.save(substanceFlag);
			return new ResponseEntity<>(_substanceFlag, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating substance flag");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSubstanceFlag(@PathVariable("id") Integer id) {
		try {
			substanceStructureFlagRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting substance flag");
		}
	}

}