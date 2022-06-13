package gov.epa.analyticalqc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.entity.Call;
import gov.epa.analyticalqc.repository.CallRepository;

@RestController
@RequestMapping("/api/calls")
public class CallController {
    
    @Autowired CallRepository callRepository;

    @GetMapping()
    public ResponseEntity<List<Call>> getAllCalls() {
        return new ResponseEntity<>(callRepository.findAll(Sort.by("id").ascending()), HttpStatus.OK);
    }

}