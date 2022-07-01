package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.NmrAmenFlag;
import lombok.Value;

@Value
public class AmenabilityPredictionDto {

    private Integer id;

    private Double lcmsAmenNeg;

    private Double lcmsAmenPos;

    private Boolean lcmsAmenNegTrue;

    private Boolean lcmsAmenPosTrue;

    private NmrAmenFlag nmrAmenFlag;
    
}
