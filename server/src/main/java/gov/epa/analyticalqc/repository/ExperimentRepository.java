package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.epa.analyticalqc.dto.ExperimentDto;
import gov.epa.analyticalqc.entity.Experiment;

public interface ExperimentRepository extends JpaRepository<Experiment, Integer> {

    Optional<Experiment> findById(Integer id);

    List<Experiment> findBySampleId(Integer sampleId);

    @Query("SELECT new gov.epa.analyticalqc.dto.ExperimentDto(e.id, e.file, e.experimentDate, e.study, e.timepoint, e.msDetector, "
        + "e.plate, e.batch, e.well, e.row, e.col, e.grade, e.purity, e.purityFraction, e.expectedConc, e.nmrConc, e.measuredConc, "
        + "e.passFail, e.passFailBackup, e.comment, e.notes, e.origId, e.origFilesId, e.origFilesContentId, e.isTripod) from Experiment e where e.sample.id = :sampleId")
    List<ExperimentDto> findDtoBySampleId(@Param("sampleId") Integer sampleId);

}