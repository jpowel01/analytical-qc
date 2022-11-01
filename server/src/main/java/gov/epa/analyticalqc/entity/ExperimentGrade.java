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
@Table(name="experiment_grades")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExperimentGrade {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="experiment_id")
	private Experiment experiment;

    @ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;
    
    public Grade getGrade() {
    	return this.grade;
    }
}

	
