package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateListRequest {

    private String name;

    private String description;

    private String createBy;

    private String dtxsids;

    private Double mwMin;

    private Double mwMax;

    private List<Integer> t0Grades;

    private List<Integer> t4Grades;

    private List<Integer> calls;

    private Boolean hasText;

    private Boolean hasNoText;

}
