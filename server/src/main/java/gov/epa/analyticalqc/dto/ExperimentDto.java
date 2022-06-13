package gov.epa.analyticalqc.dto;

import java.util.Date;

import gov.epa.analyticalqc.entity.File;
import lombok.Value;

@Value
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

}
