package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Experiment;

public interface ExperimentRepository extends JpaRepository<Experiment, Integer> {

    Optional<Experiment> findById(Integer id);

    List<Experiment> findBySampleId(Integer sampleId);

}