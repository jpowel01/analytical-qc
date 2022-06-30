package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.SubstanceFile;

public interface SubstanceFileRepository extends JpaRepository<SubstanceFile, Integer> {

    List<SubstanceFile> findBySubstanceId(Integer substanceId);

    Optional<SubstanceFile> findBySubstanceDtxsidAndFileFileName(String dtxsid, String fileName);

}