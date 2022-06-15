package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceAnnotation;

public interface SubstanceAnnotationRepository extends JpaRepository<SubstanceAnnotation, Integer> {

    Optional<SubstanceAnnotation> findBySubstanceId(Integer substanceId);

}