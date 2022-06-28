package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ListDetail {

    private gov.epa.analyticalqc.entity.List list;

    private List<Integer> substanceIds;

}
