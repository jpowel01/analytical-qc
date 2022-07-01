package gov.epa.analyticalqc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.entity.Flag;
import gov.epa.analyticalqc.repository.FlagRepository;

@RestController
@RequestMapping("/api/flags")
public class FlagController {
    
    @Autowired FlagRepository flagRepository;

    @GetMapping()
    public ResponseEntity<List<Flag>> getAllFlags() {
        return new ResponseEntity<>(flagRepository.findAll(Sort.by("id").ascending()), HttpStatus.OK);
    }

}