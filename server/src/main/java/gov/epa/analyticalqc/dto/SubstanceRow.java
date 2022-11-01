package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.Call;
import gov.epa.analyticalqc.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value @Getter @Setter
public class SubstanceRow {

    public Integer id;

    public String dtxsid;

    public String preferredName;

    public String casrn;

    public String molFormula;

    public Double molWeight;

    public Long pubchemCid;

    public Grade t0Grade;

    public Grade t4Grade;

    public Call call;

    public Boolean validated;
    
    
    public SubstanceRow() {
    	
    }
    
    public SubstanceRow(Integer id, String dtxsid, String preferredName, String casrn, String molFormula, Double molWeight, Long pubchemCid, Grade t0Grade, Grade t4Grade, Call call, Boolean validated) {
    	this.id=id;
    	this.dtxsid=dtxsid;
    	this.preferredName=preferredName;
    	this.casrn=casrn;
    	this.molFormula=molFormula;
    	this.molWeight=molWeight;
    	this.pubchemCid=pubchemCid;
    	this.t0Grade=t0Grade;
    	this.t4Grade=t4Grade;
    	this.call=call;
    	this.validated=validated;
    }

    
}
