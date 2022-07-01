package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.SubstanceAnnotation;

public interface SubstanceAnnotationRepository extends JpaRepository<SubstanceAnnotation, Integer> {

    Optional<SubstanceAnnotation> findBySubstanceId(Integer substanceId);

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.annotation is not null")
    List<Integer> findAnnotatedSubstanceIds();

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.annotation is null")
    List<Integer> findUnannotatedSubstanceIds();

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.t0Grade.id in (:gradeIds)")
    List<Integer> findSubstanceIdsByT0GradeIdIn(List<Integer> gradeIds);

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.t0Grade.id is null")
    List<Integer> findT0UngradedSubstanceIds();

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.t4Grade.id in (:gradeIds)")
    List<Integer> findSubstanceIdsByT4GradeIdIn(List<Integer> gradeIds);

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.t4Grade.id is null")
    List<Integer> findT4UngradedSubstanceIds();

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.call.id in (:callIds)")
    List<Integer> findSubstanceIdsByCallIdIn(List<Integer> callIds);

    @Query("select distinct sa.substance.id from SubstanceAnnotation sa where sa.call.id is null")
    List<Integer> findUncalledSubstanceIds();

}