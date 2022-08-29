package gov.epa.analyticalqc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="lists")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class List {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Boolean hidden;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
    
    public Boolean getHidden() {
    	return hidden;
    }
    
    public void setHidden(Boolean hidden) {
    	this.hidden = hidden;
    }
}

	
