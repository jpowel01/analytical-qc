package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubstanceListRequest {

    private List<Integer> ids;

    private Integer pageNo;

    private Integer pageSize;
}
