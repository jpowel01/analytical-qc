package gov.epa.analyticalqc.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.SubstanceCall;

public interface SubstanceCallRepository extends JpaRepository<SubstanceCall, Integer> {

    Optional<SubstanceCall> findBySubstanceId(Integer substanceId);

    @Query("select distinct sc.substance.id from SubstanceCall sc where sc.call.id in (:callIds)")
    List<Integer> findSubstanceIdsByCallIdIn(List<Integer> callIds);

}