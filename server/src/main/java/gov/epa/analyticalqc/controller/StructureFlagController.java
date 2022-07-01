package gov.epa.analyticalqc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.entity.StructureFlag;
import gov.epa.analyticalqc.repository.StructureFlagRepository;

@RestController
@RequestMapping("/api/structure-flags")
public class StructureFlagController {
    
    @Autowired StructureFlagRepository structureFlagRepository;

    @GetMapping()
    public ResponseEntity<List<StructureFlag>> getAllStructureFlags() {
        return new ResponseEntity<>(structureFlagRepository.findAll(Sort.by("id").ascending()), HttpStatus.OK);
    }

}