package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.epa.analyticalqc.dto.SampleDto;
import gov.epa.analyticalqc.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Integer> {

    Optional<Sample> findById(Integer id);

    Optional<Sample> findByNcgcId(String ncgcId);

    Optional<Sample> findByTox21Id(Long tox21Id);

    Optional<Sample> findByBottleBarcode(String bottleBarcode);

    List<Sample> findBySubstanceId(Integer substanceId);

    @Query("SELECT new gov.epa.analyticalqc.dto.SampleDto(s.id, s.ncgcId, s.tox21Id, s.pubchemSid, s.bottleBarcode, s.withdrawn) from Sample s where s.substance.id = :substanceId")
    List<SampleDto> findDtoBySubstanceId(@Param("substanceId") Integer substanceId);

}