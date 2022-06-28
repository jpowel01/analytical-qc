package gov.epa.analyticalqc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="list_substances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ListSubstance {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="substance_id")
	private Substance substance;

    @ManyToOne
	@JoinColumn(name="list_id")
	private List list;
    
}
