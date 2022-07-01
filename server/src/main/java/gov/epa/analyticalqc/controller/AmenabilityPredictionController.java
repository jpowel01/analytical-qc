package gov.epa.analyticalqc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.entity.AmenabilityPrediction;
import gov.epa.analyticalqc.repository.AmenabilityPredictionRepository;

@RestController
@RequestMapping("/api/amenability-predictions")
public class AmenabilityPredictionController {
    
    @Autowired AmenabilityPredictionRepository amenabilityPredictionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AmenabilityPrediction> getAmenabilityPrediction(@PathVariable("id") Integer id) {
        Optional<AmenabilityPrediction> findAmenabilityPrediction = amenabilityPredictionRepository.findById(id);
        if (findAmenabilityPrediction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample annotation not found");
        } else {
            return new ResponseEntity<>(findAmenabilityPrediction.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<AmenabilityPrediction> getAmenabilityPredictionBySubstanceId(@PathVariable("substance-id") Integer id) {
        Optional<AmenabilityPrediction> findAmenabilityPrediction = amenabilityPredictionRepository.findBySubstanceId(id);
        if (findAmenabilityPrediction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Amenability prediction not found");
        } else {
            return new ResponseEntity<>(findAmenabilityPrediction.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenabilityPrediction> updateAmenabilityPrediction(@PathVariable("id") Integer id, @RequestBody AmenabilityPrediction amenabilityPrediction) {
        Optional<AmenabilityPrediction> findAmenabilityPrediction = amenabilityPredictionRepository.findById(id);
        if (findAmenabilityPrediction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Amenability prediction not found");
        } else {
            return new ResponseEntity<>(amenabilityPredictionRepository.save(amenabilityPrediction), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<AmenabilityPrediction> createAmenabilityPrediction(@RequestBody AmenabilityPrediction amenabilityPrediction) {
        try {
			AmenabilityPrediction _amenabilityPrediction = amenabilityPredictionRepository.save(amenabilityPrediction);
			return new ResponseEntity<>(_amenabilityPrediction, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating amenability prediction");
		}
    }

}