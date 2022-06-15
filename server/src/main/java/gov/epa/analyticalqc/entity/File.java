package gov.epa.analyticalqc.entity;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class File {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_date")
    private Date fileDate;

    private String hash;

    private Long bytecount;
    
}
