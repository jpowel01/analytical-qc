package gov.epa.analyticalqc.dto;

import lombok.*;

@Data
public class SubstanceFileDto {

    private String fileName;

    private String note;

    public SubstanceFileDto() {

    }

    public SubstanceFileDto(String fileName, String note) {
    	this.fileName=fileName;
    	this.note=note;
    }


}
