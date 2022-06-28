package gov.epa.analyticalqc.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.dto.ListDetail;
import gov.epa.analyticalqc.repository.ListRepository;
import gov.epa.analyticalqc.repository.ListSubstanceRepository;

@RestController
@RequestMapping("/api/lists")
public class ListController {
    
    @Autowired ListRepository listRepository;
    @Autowired ListSubstanceRepository listSubstanceRepository;

    @GetMapping()
    public ResponseEntity<List<gov.epa.analyticalqc.entity.List>> getAllLists() {
        return new ResponseEntity<>(listRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ListDetail> getListDetail(@PathVariable("id") Integer id) {
        gov.epa.analyticalqc.entity.List list = null;
        try {
            list = listRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        }
        
        List<Integer> substanceIds = listSubstanceRepository.findSubstanceIdsByListId(id);
        return new ResponseEntity<>(new ListDetail(list, substanceIds), HttpStatus.OK);
    }
}