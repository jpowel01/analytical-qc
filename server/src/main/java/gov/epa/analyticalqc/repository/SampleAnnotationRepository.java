package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SampleAnnotation;

public interface SampleAnnotationRepository extends JpaRepository<SampleAnnotation, Integer> {

    Optional<SampleAnnotation> findBySampleId(Integer sampleId);

}