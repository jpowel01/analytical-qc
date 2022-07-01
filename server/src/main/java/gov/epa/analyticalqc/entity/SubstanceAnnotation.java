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
@Table(name="substance_annotations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubstanceAnnotation {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="substance_id")
	private Substance substance;

    @ManyToOne
	@JoinColumn(name="t0_grade_id")
	private Grade t0Grade;

    @ManyToOne
	@JoinColumn(name="t4_grade_id")
	private Grade t4Grade;

    @ManyToOne
	@JoinColumn(name="call_id")
	private Call call;

    private Boolean validated;

    private String annotation;
    
}
