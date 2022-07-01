package gov.epa.analyticalqc.entity;

import javax.persistence.Column;
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
@Table(name="substance_structure_flags")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubstanceStructureFlag {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="substance_id")
	private Substance substance;

    @ManyToOne
	@JoinColumn(name="structure_flag_id")
	private StructureFlag structureFlag;

    @Column(name="validated")
    private Boolean validated;
    
}
