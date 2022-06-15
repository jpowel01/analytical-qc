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

import gov.epa.analyticalqc.entity.SampleAnnotation;
import gov.epa.analyticalqc.repository.SampleAnnotationRepository;

@RestController
@RequestMapping("/api/sample-annotations")
public class SampleAnnotationController {
    
    @Autowired SampleAnnotationRepository sampleAnnotationRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SampleAnnotation> getSampleAnnotation(@PathVariable("id") Integer id) {
        Optional<SampleAnnotation> findSampleAnnotation = sampleAnnotationRepository.findById(id);
        if (findSampleAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample annotation not found");
        } else {
            return new ResponseEntity<>(findSampleAnnotation.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/sample-id/{sample-id}")
    public ResponseEntity<SampleAnnotation> getSampleAnnotationBySampleId(@PathVariable("sample-id") Integer id) {
        Optional<SampleAnnotation> findSampleAnnotation = sampleAnnotationRepository.findBySampleId(id);
        if (findSampleAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample annotation not found");
        } else {
            return new ResponseEntity<>(findSampleAnnotation.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleAnnotation> updateSampleAnnotation(@PathVariable("id") Integer id, @RequestBody SampleAnnotation sampleAnnotation) {
        Optional<SampleAnnotation> findSampleAnnotation = sampleAnnotationRepository.findById(id);
        if (findSampleAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample annotation not found");
        } else {
            return new ResponseEntity<>(sampleAnnotationRepository.save(sampleAnnotation), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SampleAnnotation> createSampleAnnotation(@RequestBody SampleAnnotation sampleAnnotation) {
        try {
			SampleAnnotation _sampleAnnotation = sampleAnnotationRepository.save(sampleAnnotation);
			return new ResponseEntity<>(_sampleAnnotation, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sample annotation");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSampleAnnotation(@PathVariable("id") Integer id) {
		try {
			sampleAnnotationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting sample annotation");
		}
	}

}