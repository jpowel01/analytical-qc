package gov.epa.analyticalqc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="substances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Substance {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String dtxsid;

    @Column(name="preferred_name")
    private String preferredName;

    private String casrn;

    @Column(name="mol_formula")
    private String molFormula;

    @Column(name="mol_weight")
    private Double molWeight;

    @Column(name="pubchem_cid")
    private Long pubchemCid;

    @OneToOne(mappedBy="substance")
    @JsonManagedReference
    private PropertyPrediction propertyPrediction;
    
}
