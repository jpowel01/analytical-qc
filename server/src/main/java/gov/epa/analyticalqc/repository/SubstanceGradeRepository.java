package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.SubstanceGrade;

public interface SubstanceGradeRepository extends JpaRepository<SubstanceGrade, Integer> {

    List<SubstanceGrade> findBySubstanceId(Integer substanceId);

    @Query("select distinct sg.substance.id from SubstanceGrade sg where sg.grade.id in (:gradeIds) and sg.t0_t4 = :t0_t4")
    List<Integer> findSubstanceIdsByGradeIdIn(List<Integer> gradeIds, Boolean t0_t4);

}