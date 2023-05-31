package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.NmrAmenFlag;
import lombok.*;

@Data
public class AmenabilityPredictionDto {

    private Integer id;

    private Double lcmsAmenNeg;

    private Double lcmsAmenPos;

    private Boolean lcmsAmenNegTrue;

    private Boolean lcmsAmenPosTrue;

    private NmrAmenFlag nmrAmenFlag;

    public AmenabilityPredictionDto() {

    }

    public AmenabilityPredictionDto(Integer id, double lcmsAmenNeg, double lcmsAmenPos, boolean lcmsAmenNegTrue, boolean lcmsAmenPosTrue, NmrAmenFlag nmrAmenFlag) {
    	this.id=id;
    	this.lcmsAmenNeg=lcmsAmenNeg;
    	this.lcmsAmenPos=lcmsAmenPos;
    	this.lcmsAmenNegTrue=lcmsAmenNegTrue;
    	this.lcmsAmenPosTrue=lcmsAmenPosTrue;
    	this.nmrAmenFlag=nmrAmenFlag;
    }



}
