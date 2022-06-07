package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Integer> {

    Optional<Sample> findById(Integer id);

    Optional<Sample> findByNcgcId(String ncgcId);

    Optional<Sample> findByTox21Id(Integer tox21Id);

    Optional<Sample> findByBottleBarcode(String bottleBarcode);

    List<Sample> findBySubstanceId(Integer substanceId);

}