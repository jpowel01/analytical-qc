package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Flag;

public interface FlagRepository extends JpaRepository<Flag, Integer> {

    Optional<Flag> findById(Integer id);

    Optional<Flag> findByFlag(String flag);

}