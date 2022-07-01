package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.epa.analyticalqc.dto.AmenabilityPredictionDto;
import gov.epa.analyticalqc.entity.AmenabilityPrediction;

public interface AmenabilityPredictionRepository extends JpaRepository<AmenabilityPrediction, Integer> {

    Optional<AmenabilityPrediction> findById(Integer id);

    Optional<AmenabilityPrediction> findBySubstanceId(Integer substanceId);

    @Query("SELECT new gov.epa.analyticalqc.dto.AmenabilityPredictionDto(ap.lcmsAmenNeg, ap.lcmsAmenPos) from AmenabilityPrediction ap where ap.substance.id = :substanceId")
    Optional<AmenabilityPredictionDto> findDtoBySubstanceId(@Param("substanceId") Integer substanceId);

}