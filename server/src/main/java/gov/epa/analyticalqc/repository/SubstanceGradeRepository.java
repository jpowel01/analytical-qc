package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceGrade;

public interface SubstanceGradeRepository extends JpaRepository<SubstanceGrade, Integer> {

    List<SubstanceGrade> findBySubstanceId(Integer substanceId);

}