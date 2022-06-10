package gov.epa.analyticalqc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.dto.SubstanceFlagDto;
import gov.epa.analyticalqc.repository.SubstanceFlagRepository;

@RestController
@RequestMapping("/api/substance-flags")
@CrossOrigin(origins = "http://v2626umcth819.rtord.epa.gov:81")
public class SubstanceFlagController {
    
    @Autowired SubstanceFlagRepository substanceFlagRepository;

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<List<SubstanceFlagDto>> getSubstanceFlagsBySubstanceId(@PathVariable("substance-id") Integer substanceId) {
        return new ResponseEntity<>(substanceFlagRepository.findBySubstanceId(substanceId).stream().map(sf -> new SubstanceFlagDto(sf)).collect(Collectors.toList()), 
            HttpStatus.OK);
    }

}