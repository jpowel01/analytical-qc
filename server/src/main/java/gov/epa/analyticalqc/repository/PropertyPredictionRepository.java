package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.PropertyPrediction;

public interface PropertyPredictionRepository extends JpaRepository<PropertyPrediction, Integer> {

    Optional<PropertyPrediction> findById(Integer id);

    Optional<PropertyPrediction> findBySubstanceId(Integer substanceId);

}