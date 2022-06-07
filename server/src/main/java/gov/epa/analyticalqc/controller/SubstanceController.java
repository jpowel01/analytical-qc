package gov.epa.analyticalqc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/substances")
@CrossOrigin("http://localhost:8081")
public class SubstanceController {
    
    @Autowired SubstanceRepository substanceRepository;

    @GetMapping()
    public ResponseEntity<List<Substance>> getAllSubstances() {
        return new ResponseEntity<>(substanceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Substance> getSubstance(@PathVariable("id") Integer id) {
        Optional<Substance> findSubstance = substanceRepository.findById(id);
        if (findSubstance.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        } else {
            return new ResponseEntity<>(findSubstance.get(), HttpStatus.OK);
        }
    }

}