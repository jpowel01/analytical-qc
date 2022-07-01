package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceStructureFlag;

public interface SubstanceStructureFlagRepository extends JpaRepository<SubstanceStructureFlag, Integer> {

    List<SubstanceStructureFlag> findBySubstanceId(Integer substanceId);

}