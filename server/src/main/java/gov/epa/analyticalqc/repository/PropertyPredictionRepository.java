package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.epa.analyticalqc.dto.PropertyPredictionDto;
import gov.epa.analyticalqc.entity.PropertyPrediction;

public interface PropertyPredictionRepository extends JpaRepository<PropertyPrediction, Integer> {

    Optional<PropertyPrediction> findById(Integer id);

    Optional<PropertyPrediction> findBySubstanceId(Integer substanceId);

    @Query("SELECT new gov.epa.analyticalqc.dto.PropertyPredictionDto(pp.bp, pp.mp, pp.vp, pp.logP) from PropertyPrediction pp where pp.substance.id = :substanceId")
    Optional<PropertyPredictionDto> findDtoBySubstanceId(@Param("substanceId") Integer substanceId);

}