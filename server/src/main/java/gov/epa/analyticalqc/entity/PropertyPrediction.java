package gov.epa.analyticalqc.entity;

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
@Table(name="property_predictions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropertyPrediction {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="substance_id")
	private Substance substance;

    @Column(name="bp_degc")
    private Double bpDegC;

    @Column(name="mp_degc")
    private Double mpDegC;

    @Column(name="vp_mmhg")
    private Double vpMmhg;

    @Column(name="logp")
    private Double logP;
    
}
