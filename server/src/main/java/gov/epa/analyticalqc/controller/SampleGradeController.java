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

import gov.epa.analyticalqc.entity.SampleGrade;
import gov.epa.analyticalqc.repository.SampleGradeRepository;

@RestController
@RequestMapping("/api/sample-grades")
public class SampleGradeController {
    
    @Autowired SampleGradeRepository sampleGradeRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SampleGrade> getSampleGrade(@PathVariable("id") Integer id) {
        Optional<SampleGrade> findSampleGrade = sampleGradeRepository.findById(id);
        if (findSampleGrade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample grade not found");
        } else {
            return new ResponseEntity<>(findSampleGrade.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/sample-id/{sample-id}")
    public ResponseEntity<List<SampleGrade>> getSampleGradesBySampleId(@PathVariable("sample-id") Integer id) {
        return new ResponseEntity<>(sampleGradeRepository.findBySampleId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleGrade> updateSampleGrade(@PathVariable("id") Integer id, @RequestBody SampleGrade sampleGrade) {
        Optional<SampleGrade> findSampleGrade = sampleGradeRepository.findById(id);
        if (findSampleGrade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample grade not found");
        } else {
            return new ResponseEntity<>(sampleGradeRepository.save(sampleGrade), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SampleGrade> createSampleGrade(@RequestBody SampleGrade sampleGrade) {
        try {
			SampleGrade _sampleGrade = sampleGradeRepository.save(sampleGrade);
			return new ResponseEntity<>(_sampleGrade, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sample grade");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSampleGrade(@PathVariable("id") Integer id) {
		try {
			sampleGradeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting sample grade");
		}
	}

}