package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SampleDto {

    private String ncgcId;

    private Long tox21Id;

    private Long pubchemSid;

    private String bottleBarcode;

    private boolean withdrawn;

    public SampleDto(Sample sample) {
        this.setNcgcId(sample.getNcgcId());
        this.setTox21Id(sample.getTox21Id());
        this.setPubchemSid(sample.getPubchemSid());
        this.setBottleBarcode(sample.getBottleBarcode());
        this.setWithdrawn(sample.isWithdrawn());
    }
    
}
