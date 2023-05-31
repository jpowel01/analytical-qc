package gov.epa.analyticalqc.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateSubstanceFilesRequest {

    private String files;

    private Boolean createSubstances;

    private String name;

    private String description;

}
