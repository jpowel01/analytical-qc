package gov.epa.analyticalqc.entity;

import java.sql.Date;

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

}
