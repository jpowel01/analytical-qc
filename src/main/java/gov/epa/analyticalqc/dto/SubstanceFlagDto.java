package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.Flag;
import gov.epa.analyticalqc.entity.SubstanceFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubstanceFlagDto {

	private Flag flag;

    private Boolean validated;

    public SubstanceFlagDto(SubstanceFlag substanceFlag) {
        this.setFlag(substanceFlag.getFlag());
        this.setValidated(substanceFlag.getValidated());
    }
    
}
