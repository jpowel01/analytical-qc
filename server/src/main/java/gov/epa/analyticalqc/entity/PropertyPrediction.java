package gov.epa.analyticalqc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @OneToOne
	@JoinColumn(name="substance_id")
    @JsonBackReference
	private Substance substance;

    @Column(name="bp_degc")
    private Double bp;

    @Column(name="mp_degc")
    private Double mp;

    @Column(name="vp_mmhg")
    private Double vp;

    @Column(name="logp")
    private Double logP;
    
}
