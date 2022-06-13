package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    Optional<Grade> findById(Integer id);

    Optional<Grade> findByGrade(String grade);

}