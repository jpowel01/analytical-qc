package gov.epa.analyticalqc.controller;

import java.util.NoSuchElementException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gov.epa.analyticalqc.entity.Sample;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceAnnotation;
import gov.epa.analyticalqc.repository.SampleRepository;
import gov.epa.analyticalqc.repository.SubstanceAnnotationRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;

@RestController
@RequestMapping("/api/substance-annotations")
public class SubstanceAnnotationController {
    
    @Autowired SubstanceAnnotationRepository substanceAnnotationRepository;
    @Autowired SubstanceRepository substanceRepository;
    @Autowired SampleRepository sampleRepository;

    @GetMapping()
    public ResponseEntity<SubstanceAnnotation> getSubstanceAnnotation(@RequestParam("type") String type, @RequestParam("query") String query) {
        Substance substance = null;
        Sample sample = null;
        type = type.toLowerCase();

        switch (type) {
            case "id":
                Integer queryInt = Integer.parseInt(query.replaceAll("[^0-9]",""));
                substance = substanceRepository.findById(queryInt).orElse(null);
                break;
            case "cas":
            case "casrn":
                substance = substanceRepository.findByCasrn(query).orElse(null);
                break;
            case "dtxsid":
                substance = substanceRepository.findByDtxsid(query).orElse(null);
                break;
            case "tox21":
            case "tox21-id":
                Long queryLong = Long.parseLong(query.replaceAll("Tox21_",""));
                sample = sampleRepository.findByTox21Id(queryLong).orElse(null);
                break;
            case "ncgc":
            case "ncgc-id":
                sample = sampleRepository.findByNcgcId(query).orElse(null);
                break;
            case "barcode":
            case "bottle-barcode":
                sample = sampleRepository.findByBottleBarcode(query).orElse(null);
                break;
            default:
                substance = null;
                sample = null;
                break;
        }

        if (substance == null && sample != null) {
            substance = sample.getSubstance();
        }

        if (substance != null) {
            try {
                return new ResponseEntity<>(substanceAnnotationRepository.findBySubstanceId(substance.getId()).orElseThrow(), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance annotation not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceAnnotation> getSubstanceAnnotation(@PathVariable("id") Integer id) {
        Optional<SubstanceAnnotation> findSubstanceAnnotation = substanceAnnotationRepository.findById(id);
        if (findSubstanceAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance annotation not found");
        } else {
            return new ResponseEntity<>(findSubstanceAnnotation.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/substance-id/{substance-id}")
    public ResponseEntity<SubstanceAnnotation> getSubstanceAnnotationBySubstanceId(@PathVariable("substance-id") Integer id) {
        Optional<SubstanceAnnotation> findSubstanceAnnotation = substanceAnnotationRepository.findBySubstanceId(id);
        if (findSubstanceAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance annotation not found");
        } else {
            return new ResponseEntity<>(findSubstanceAnnotation.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceAnnotation> updateSubstanceAnnotation(@PathVariable("id") Integer id, @RequestBody SubstanceAnnotation substanceAnnotation) {
        Optional<SubstanceAnnotation> findSubstanceAnnotation = substanceAnnotationRepository.findById(id);
        if (findSubstanceAnnotation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Substance annotation not found");
        } else {
            return new ResponseEntity<>(substanceAnnotationRepository.save(substanceAnnotation), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SubstanceAnnotation> createSubstanceAnnotation(@RequestBody SubstanceAnnotation substanceAnnotation) {
        try {
			SubstanceAnnotation _substanceAnnotation = substanceAnnotationRepository.save(substanceAnnotation);
			return new ResponseEntity<>(_substanceAnnotation, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating substance annotation");
		}
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSubstanceAnnotation(@PathVariable("id") Integer id) {
		try {
			substanceAnnotationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting substance annotation");
		}
	}

}