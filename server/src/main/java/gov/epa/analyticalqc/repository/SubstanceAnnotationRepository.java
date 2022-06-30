package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.SubstanceAnnotation;

public interface SubstanceAnnotationRepository extends JpaRepository<SubstanceAnnotation, Integer> {

    Optional<SubstanceAnnotation> findBySubstanceId(Integer substanceId);

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa")
    List<Integer> findAnnotatedSubstanceIds();

}