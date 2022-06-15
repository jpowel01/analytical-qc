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

import gov.epa.analyticalqc.entity.SubstanceGrade;
import gov.epa.analyticalqc.repository.SubstanceGradeRepository;

@RestController
@RequestMapping("/api/substance-grades")
public class SubstanceGradeController {
    
    @Autowired SubstanceGradeRepository substanceGradeRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceGrade> getSubstanceGrade(@PathVariable("id") Integer id) {
        Optional<SubstanceGrade> findSubstanceGrade = substanceGradeRepository.findById(id);
        if (findSubstanceGrade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance grade not found");
        } else {
            return new ResponseEntity<>(findSubstanceGrade.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<List<SubstanceGrade>> getSubstanceGradesBySubstanceId(@PathVariable("substance-id") Integer id) {
        return new ResponseEntity<>(substanceGradeRepository.findBySubstanceId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceGrade> updateSubstanceGrade(@PathVariable("id") Integer id, @RequestBody SubstanceGrade substanceGrade) {
        Optional<SubstanceGrade> findSubstanceGrade = substanceGradeRepository.findById(id);
        if (findSubstanceGrade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance grade not found");
        } else {
            return new ResponseEntity<>(substanceGradeRepository.save(substanceGrade), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SubstanceGrade> createSubstanceGrade(@RequestBody SubstanceGrade substanceGrade) {
        try {
			SubstanceGrade _substanceGrade = substanceGradeRepository.save(substanceGrade);
			return new ResponseEntity<>(_substanceGrade, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating substance grade");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSubstanceGrade(@PathVariable("id") Integer id) {
		try {
			substanceGradeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting substance grade");
		}
	}

}