package gov.epa.analyticalqc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.analyticalqc.entity.Grade;
import gov.epa.analyticalqc.repository.GradeRepository;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    
    @Autowired GradeRepository gradeRepository;

    @GetMapping()
    public ResponseEntity<List<Grade>> getAllGrades() {
        return new ResponseEntity<>(gradeRepository.findAll(Sort.by("id").ascending()), HttpStatus.OK);
    }

}