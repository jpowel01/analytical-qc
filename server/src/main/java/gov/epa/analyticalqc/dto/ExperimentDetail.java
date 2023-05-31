package gov.epa.analyticalqc.dto;

import java.util.List;

import gov.epa.analyticalqc.entity.Grade;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ExperimentDetail {

    private ExperimentDto experiment;

    private List<Grade> grades;

}
