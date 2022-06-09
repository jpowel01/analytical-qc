package gov.epa.analyticalqc.dto;

import java.sql.Date;

import gov.epa.analyticalqc.entity.Experiment;
import gov.epa.analyticalqc.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExperimentDto {

	private File file;
    
    private Date experimentDate;

    private String study;

    private String timepoint;

    private String msDetector;

    private Integer plate;

    private String batch;

    private String well;

    private String row;

    private Integer col;

    private String grade;

    private String purity;

    private Double purityFraction;

    private Double expectedConc;

    private Double nmrConc;

    private Double measuredConc;

    private String passFail;

    private String passFailBackup;

    private String comment;

    private String notes;

    private Integer origId;

    private Integer origFilesId;

    private Integer origFilesContentId;

    private Integer isTripod;

    public ExperimentDto(Experiment experiment) {
        this.setFile(experiment.getFile());
        this.setExperimentDate(experiment.getExperimentDate());
        this.setStudy(experiment.getStudy());
        this.setTimepoint(experiment.getTimepoint());
        this.setMsDetector(experiment.getMsDetector());
        this.setPlate(experiment.getPlate());
        this.setBatch(experiment.getBatch());
        this.setWell(experiment.getWell());
        this.setRow(experiment.getRow());
        this.setCol(experiment.getCol());
        this.setGrade(experiment.getGrade());
        this.setPurity(experiment.getPurity());
        this.setPurityFraction(experiment.getPurityFraction());
        this.setExpectedConc(experiment.getExpectedConc());
        this.setNmrConc(experiment.getNmrConc());
        this.setMeasuredConc(experiment.getMeasuredConc());
        this.setPassFail(experiment.getPassFail());
        this.setPassFailBackup(experiment.getPassFailBackup());
        this.setComment(experiment.getComment());
        this.setNotes(experiment.getNotes());
        this.setOrigId(experiment.getOrigId());
        this.setOrigFilesId(experiment.getOrigFilesId());
        this.setOrigFilesContentId(experiment.getOrigFilesContentId());
        this.setIsTripod(experiment.getIsTripod());
    }

}
