package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SampleGrade;

public interface SampleGradeRepository extends JpaRepository<SampleGrade, Integer> {

    List<SampleGrade> findBySampleId(Integer sampleId);

}