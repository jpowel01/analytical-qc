package gov.epa.analyticalqc.entity;

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
@Table(name="sample_grades")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SampleGrade {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="sample_id")
	private Sample sample;

    @ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;

    private Boolean t0_t4;

    private Boolean validated;
    
}
