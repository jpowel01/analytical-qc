package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceCall;

public interface SubstanceCallRepository extends JpaRepository<SubstanceCall, Integer> {

    Optional<SubstanceCall> findBySubstanceId(Integer substanceId);

}