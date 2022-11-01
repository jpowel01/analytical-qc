package gov.epa.analyticalqc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
    
    // no args constructor
	public CreateListRequest() {
		
	}
	
	public CreateListRequest(String name, String description, String createBy, String dtxsids,
			Double mwMin, Double mwMax, List<Integer> t0Grades, List<Integer> t4Grades,
			List<Integer> calls, Boolean hasText, Boolean hasNoText) {
		this.name = name;
		this.description = description;
		this.createBy = createBy;
		this.dtxsids = dtxsids;
		this.mwMin = mwMin;
		this.mwMax = mwMax;
		this.t0Grades = t0Grades;
		this.t4Grades = t4Grades;
		this.calls = calls;
		this.hasText = hasText;
		this.hasNoText = hasNoText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getDtxsids() {
		return dtxsids;
	}

	public void setDtxsids(String dtxsids) {
		this.dtxsids = dtxsids;
	}

	public Double getMwMin() {
		return mwMin;
	}

	public void setMwMin(Double mwMin) {
		this.mwMin = mwMin;
	}

	public Double getMwMax() {
		return mwMax;
	}

	public void setMwMax(Double mwMax) {
		this.mwMax = mwMax;
	}

	public List<Integer> getT0Grades() {
		return t0Grades;
	}

	public void setT0Grades(List<Integer> t0Grades) {
		this.t0Grades = t0Grades;
	}

	public List<Integer> getT4Grades() {
		return t4Grades;
	}

	public void setT4Grades(List<Integer> t4Grades) {
		this.t4Grades = t4Grades;
	}

	public List<Integer> getCalls() {
		return calls;
	}

	public void setCalls(List<Integer> calls) {
		this.calls = calls;
	}

	public Boolean getHasText() {
		return hasText;
	}

	public void setHasText(Boolean hasText) {
		this.hasText = hasText;
	}

	public Boolean getHasNoText() {
		return hasNoText;
	}

	public void setHasNoText(Boolean hasNoText) {
		this.hasNoText = hasNoText;
	}
	
	
    
}
