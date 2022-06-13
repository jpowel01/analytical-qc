package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SampleCall;

public interface SampleCallRepository extends JpaRepository<SampleCall, Integer> {

    Optional<SampleCall> findBySampleId(Integer sampleId);

}