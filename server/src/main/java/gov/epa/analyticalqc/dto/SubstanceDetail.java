package gov.epa.analyticalqc.dto;

import java.util.List;

import gov.epa.analyticalqc.entity.Substance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubstanceDetail {

    private Substance substance;

    private PropertyPredictionDto propertyPrediction;

    private List<SampleDetail> sampleDetails;

    private List<SubstanceFileDto> substanceFiles;

}
