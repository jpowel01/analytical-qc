package gov.epa.analyticalqc.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="substances", indexes={@Index(name="dtxsid_idx", columnList="dtxsid", unique=true)})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Substance {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String dtxsid;

    @Column(name="preferred_name")
    private String preferredName;

    private String casrn;

    @Column(name="mol_formula")
    private String molFormula;

    @Column(name="mol_weight")
    private Double molWeight;

    @Column(name="pubchem_cid")
    private Long pubchemCid;

    @OneToOne(mappedBy="substance")
    @JsonIgnore
    private SubstanceAnnotation substanceAnnotation;

    @Column(name="updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="created_by")
    private String createdBy;


}
