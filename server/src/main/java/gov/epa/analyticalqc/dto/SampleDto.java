package gov.epa.analyticalqc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value @Getter @Setter
public class SampleDto {

    private Integer id;

    private String ncgcId;

    private Long tox21Id;

    private Long pubchemSid;

    private String bottleBarcode;

    private Boolean withdrawn;
    
    
    public SampleDto() {
    	
    }
    
    public SampleDto(Integer id, String ncgcId, Long tox21Id, Long pubchemSid, String bottleBarcode, Boolean withdrawn) {
    	this.id=id;
    	this.ncgcId=ncgcId;
    	this.tox21Id=tox21Id;
    	this.pubchemSid=pubchemSid;
    	this.bottleBarcode=bottleBarcode;
    	this.withdrawn=withdrawn;
    }
    
    public Integer getId() {
    	return this.id;
    }

    
}
