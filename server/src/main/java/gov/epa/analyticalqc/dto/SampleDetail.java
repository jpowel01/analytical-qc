package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SampleDetail {

    private SampleDto sample;

    private List<ExperimentDetail> experiments;

    public SampleDetail() {
    	
    }
    
    public SampleDetail(SampleDto sample, List<ExperimentDetail> experiments) {
    	this.sample = sample;
    	this.experiments = experiments;
    }
}