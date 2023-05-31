package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class SampleDetail {

    private SampleDto sample;

    private List<ExperimentDetail> experiments;

}
