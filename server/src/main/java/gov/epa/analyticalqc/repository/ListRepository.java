package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<gov.epa.analyticalqc.entity.List, Integer> {

    Optional<gov.epa.analyticalqc.entity.List> findByName(String listName);

    Long deleteByName(String name);

}