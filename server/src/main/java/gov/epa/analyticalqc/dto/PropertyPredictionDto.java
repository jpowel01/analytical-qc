package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.PropertyPrediction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropertyPredictionDto {

    private Double bp;

    private Double mp;

    private Double vp;

    private Double logP;

    public PropertyPredictionDto(PropertyPrediction propertyPrediction) {
        this.setBp(propertyPrediction.getBp());
        this.setMp(propertyPrediction.getMp());
        this.setVp(propertyPrediction.getVp());
        this.setLogP(propertyPrediction.getLogP());
    }
    
}
