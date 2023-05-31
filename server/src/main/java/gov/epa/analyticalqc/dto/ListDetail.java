package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ListDetail {

    private gov.epa.analyticalqc.entity.List list;

    private List<Integer> substanceIds;

}
