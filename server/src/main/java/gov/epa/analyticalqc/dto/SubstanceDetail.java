package gov.epa.analyticalqc.dto;

import java.util.List;

import gov.epa.analyticalqc.entity.Substance;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubstanceDetail {

    private Substance substance;

    private PropertyPredictionDto propertyPrediction;

    private AmenabilityPredictionDto amenabilityPrediction;

    private List<SampleDetail> sampleDetails;

    private List<SubstanceFileDto> substanceFiles;

}
