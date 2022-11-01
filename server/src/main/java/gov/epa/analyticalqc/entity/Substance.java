package gov.epa.analyticalqc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private SubstanceAnnotation substanceAnnotation;
    
    public Substance() {
    	
    }
    
    public Substance(Integer id, String dtxsid, String preferredName, String casrn, String molFormula, Double molWeight,
    		Long pubchemCid, SubstanceAnnotation substanceAnnotation) {
    	this.id = id;
    	this.setDtxsid(dtxsid);
    	this.preferredName = preferredName;
    	this.setCasrn(casrn);
    	this.molFormula = molFormula;
    	this.molWeight = molWeight;
    	this.pubchemCid = pubchemCid;
    	this.substanceAnnotation = substanceAnnotation;
    }
    
    public Integer getId() {
    	return this.id;
    }
    
    public void SetId(Integer id) {
    	this.id = id;
    }

	public String getDtxsid() {
		return dtxsid;
	}

	public void setDtxsid(String dtxsid) {
		this.dtxsid = dtxsid;
	}
	
	public void SetPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	
	public String getPreferredName() {
		return this.preferredName;
	}

	public String getCasrn() {
		return casrn;
	}

	public void setCasrn(String casrn) {
		this.casrn = casrn;
	}

	public String getMolFormula() {
		return molFormula;
	}

	public void setMolFormula(String molFormula) {
		this.molFormula = molFormula;
	}

	public Double getMolWeight() {
		return molWeight;
	}

	public void setMolWeight(Double molWeight) {
		this.molWeight = molWeight;
	}

	public Long getPubchemCid() {
		return pubchemCid;
	}

	public void setPubchemCid(Long pubchemCid) {
		this.pubchemCid = pubchemCid;
	}

	public SubstanceAnnotation getSubstanceAnnotation() {
		return substanceAnnotation;
	}

	public void setSubstanceAnnotation(SubstanceAnnotation substanceAnnotation) {
		this.substanceAnnotation = substanceAnnotation;
	}

}
