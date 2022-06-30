package gov.epa.analyticalqc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateSubstanceFilesRequest {

    private String files;

    private Boolean createSubstances;

    private String name;

    private String description;
    
}
