package gov.epa.analyticalqc.dto;

import lombok.*;

@Data
public class PropertyPredictionDto {

    private Double bp;

    private Double mp;

    private Double vp;

    private Double logP;

    public PropertyPredictionDto() {

    }

    public PropertyPredictionDto(Double bp, Double mp, Double vp, Double logP) {
    	this.bp=bp;
    	this.mp=mp;
    	this.vp=vp;
    	this.logP=logP;
    }

}
