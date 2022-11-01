package gov.epa.analyticalqc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateSubstanceFilesRequest {

    private String files;

    private Boolean createSubstances;

    private String name;

    private String description;

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Boolean getCreateSubstances() {
		return createSubstances;
	}

	public void setCreateSubstances(Boolean createSubstances) {
		this.createSubstances = createSubstances;
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
    
}
