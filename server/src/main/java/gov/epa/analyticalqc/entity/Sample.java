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
@Table(name="samples")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Sample {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="substance_id")
	private Substance substance;

    @Column(name="ncgc_id")
    private String ncgcId;

    @Column(name="tox21_id")
    private Long tox21Id;

    @Column(name="pubchem_sid")
    private Long pubchemSid;

    @Column(name="bottle_barcode")
    private String bottleBarcode;

    private boolean withdrawn;
    
}
