package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.StructureFlag;

public interface StructureFlagRepository extends JpaRepository<StructureFlag, Integer> {

    Optional<StructureFlag> findById(Integer id);

}