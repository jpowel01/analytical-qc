package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.ExperimentAnnotation;

public interface ExperimentAnnotationRepository extends JpaRepository<ExperimentAnnotation, Integer> {

    Optional<ExperimentAnnotation> findByExperimentId(Integer experimentId);

}