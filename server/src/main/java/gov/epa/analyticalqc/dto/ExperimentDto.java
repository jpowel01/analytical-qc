package gov.epa.analyticalqc.dto;

import java.util.Date;

import gov.epa.analyticalqc.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value @Getter @Setter
public class ExperimentDto {

    private Integer id;

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
    
    public ExperimentDto() {
    	
    }
    
    public ExperimentDto(Integer id, File file, Date experimentDate, String study, String timepoint, String msDetector,
    		Integer plate, String batch, String well, String row, Integer col, String grade, String purity,
    		Double purityFraction, Double expectedConc, Double nmrConc, Double measuredConc, String passFail,
    		String passFailBackup, String comment, String notes, Integer origId, Integer origFilesId, Integer origFilesContentId,
    		Integer isTripod) {
    	this.id=id;
    	this.file=file;
    	this.experimentDate=experimentDate;
    	this.study=study;
    	this.timepoint=timepoint;
    	this.msDetector=msDetector;
    	this.plate=plate;
    	this.batch=batch;
    	this.well=well;
    	this.row=row;
    	this.col=col;
    	this.grade=grade;
    	this.purity=purity;
    	this.purityFraction=purityFraction;
    	this.expectedConc=expectedConc;
    	this.nmrConc=nmrConc;
    	this.measuredConc=measuredConc;
    	this.passFail=passFail;
    	this.passFailBackup=passFailBackup;
    	this.comment=comment;
    	this.notes=notes;
    	this.origId = origId;
    	this.origFilesContentId = origFilesId;
    	this.origFilesContentId = origFilesContentId;
    	this.isTripod=isTripod;
    }
    
    public Integer getId() {
    	return this.id;
    }


}
