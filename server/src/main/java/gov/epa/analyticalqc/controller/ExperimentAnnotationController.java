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

import gov.epa.analyticalqc.entity.ExperimentAnnotation;
import gov.epa.analyticalqc.repository.ExperimentAnnotationRepository;

@RestController
@RequestMapping("/api/experiment-annotations")
public class ExperimentAnnotationController {
    
    @Autowired ExperimentAnnotationRepository experimentAnnotationRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ExperimentAnnotation> getExperimentAnnotation(@PathVariable("id") Integer id) {
        Optional<ExperimentAnnotation> findExperimentAnnotation = experimentAnnotationRepository.findById(id);
        if (findExperimentAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiment annotation not found");
        } else {
            return new ResponseEntity<>(findExperimentAnnotation.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/experiment-id/{experiment-id}")
    public ResponseEntity<ExperimentAnnotation> getExperimentAnnotationByExperimentId(@PathVariable("experiment-id") Integer id) {
        Optional<ExperimentAnnotation> findExperimentAnnotation = experimentAnnotationRepository.findByExperimentId(id);
        if (findExperimentAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiment annotation not found");
        } else {
            return new ResponseEntity<>(findExperimentAnnotation.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperimentAnnotation> updateExperimentAnnotation(@PathVariable("id") Integer id, @RequestBody ExperimentAnnotation experimentAnnotation) {
        Optional<ExperimentAnnotation> findExperimentAnnotation = experimentAnnotationRepository.findById(id);
        if (findExperimentAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiment annotation not found");
        } else {
            return new ResponseEntity<>(experimentAnnotationRepository.save(experimentAnnotation), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ExperimentAnnotation> createExperimentAnnotation(@RequestBody ExperimentAnnotation experimentAnnotation) {
        try {
			ExperimentAnnotation _experimentAnnotation = experimentAnnotationRepository.save(experimentAnnotation);
			return new ResponseEntity<>(_experimentAnnotation, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating experiment annotation");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteExperimentAnnotation(@PathVariable("id") Integer id) {
		try {
			experimentAnnotationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting experiment annotation");
		}
	}

}