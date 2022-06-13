package gov.epa.analyticalqc.dto;

import lombok.Value;

@Value
public class SampleDto {

    private Integer id;

    private String ncgcId;

    private Long tox21Id;

    private Long pubchemSid;

    private String bottleBarcode;

    private Boolean withdrawn;
    
}
