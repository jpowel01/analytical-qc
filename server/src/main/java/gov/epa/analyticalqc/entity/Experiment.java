package gov.epa.analyticalqc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="experiments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Experiment {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="sample_id")
	private Sample sample;

    @ManyToOne
	@JoinColumn(name="file_id")
	private File file;
    
    @Column(name="experiment_date")
    private Date experimentDate;

    private String study;

    private String timepoint;

    @Column(name="ms_detector")
    private String msDetector;

    private Integer plate;

    private String batch;

    private String well;

    private String row;

    private Integer col;

    private String grade;

    private String purity;

    @Column(name="purity_fraction")
    private Double purityFraction;

    @Column(name="expected_conc")
    private Double expectedConc;

    @Column(name="nmr_conc")
    private Double nmrConc;

    @Column(name="measured_conc")
    private Double measuredConc;

    @Column(name="pass_fail")
    private String passFail;

    @Column(name="pass_fail_backup")
    private String passFailBackup;

    private String comment;

    private String notes;

    @Column(name="orig_id")
    private Integer origId;

    @Column(name="orig_files_id")
    private Integer origFilesId;

    @Column(name="orig_files_content_id")
    private Integer origFilesContentId;

    @Column(name="is_tripod")
    private Integer isTripod;

}
