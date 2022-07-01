package gov.epa.analyticalqc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.entity.NmrAmenFlag;
import gov.epa.analyticalqc.repository.NmrAmenFlagRepository;

@RestController
@RequestMapping("/api/nmr-amen-flags")
public class NmrAmenFlagController {
    
    @Autowired NmrAmenFlagRepository nmrAmenFlagRepository;

    @GetMapping()
    public ResponseEntity<List<NmrAmenFlag>> getAllFlags() {
        return new ResponseEntity<>(nmrAmenFlagRepository.findAll(Sort.by("id").ascending()), HttpStatus.OK);
    }

}