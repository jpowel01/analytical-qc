package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.dto.SubstanceFileDto;
import gov.epa.analyticalqc.entity.SubstanceFile;

public interface SubstanceFileRepository extends JpaRepository<SubstanceFile, Integer> {

    List<SubstanceFile> findBySubstanceId(Integer substanceId);

    @Query("SELECT new gov.epa.analyticalqc.dto.SubstanceFileDto(sf.file.fileName, sf.note) from SubstanceFile sf where sf.substance.id = :substanceId")
    List<SubstanceFileDto> findDtoBySubstanceId(Integer substanceId);

    Optional<SubstanceFile> findBySubstanceDtxsidAndFileFileName(String dtxsid, String fileName);

}