package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceFlag;

public interface SubstanceFlagRepository extends JpaRepository<SubstanceFlag, Integer> {

    List<SubstanceFlag> findBySubstanceId(Integer substanceId);

}