package gov.epa.analyticalqc.dto;

import java.util.List;

import gov.epa.analyticalqc.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExperimentDetail {
	
	

    private ExperimentDto experiment;

    private List<Grade> grades;
    
    public ExperimentDetail() {
    	
    }
    
    public ExperimentDetail(ExperimentDto experiment, List<Grade> grades) {
    	this.experiment = experiment;
    	this.grades = grades;
    }

}