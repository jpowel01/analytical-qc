package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubstanceListRequest {
    
    private List<Integer> ids;

    private Integer pageNo;

    private Integer pageSize;
}
