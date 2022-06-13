package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.ExperimentGrade;

public interface ExperimentGradeRepository extends JpaRepository<ExperimentGrade, Integer> {

    List<ExperimentGrade> findByExperimentId(Integer experimentId);

}