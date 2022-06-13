package gov.epa.analyticalqc.dto;

import lombok.Value;

@Value
public class PropertyPredictionDto {

    private Double bp;

    private Double mp;

    private Double vp;

    private Double logP;
    
}
