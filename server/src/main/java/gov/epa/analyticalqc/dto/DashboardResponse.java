package gov.epa.analyticalqc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DashboardResponse {
    private Integer id;
    private String dtxsid;
    private String dtxcid;
    private String casrn;
    private Integer compoundId;
    private Integer genericSubstanceId;
    private String preferredName;
    private Integer activeAssays;
    private Long cpdataCount;
    private String molFormula;
    private Double monoisotopicMass;
    private Double percentAssays;
    private Integer pubchemCount;
    private Double pubmedCount;
    private Long sourcesCount;
    private Integer qcLevel;
    private String qcLevelDesc;
    private String stereo;
    private Integer isotope;
    private Integer multicomponent;
    private Integer totalAssays;
    private String toxcastSelect;
    private Integer pubchemCid;
    private String molFile;
    private String mrvFile;
    private Long relatedSubstanceCount;
    private Long relatedStructureCount;
    private Integer hasStructureImage;
    private String iupacName;
    private String smiles;
    private String inchiString;
    private Double averageMass;
    private String inchikey;
    private String qcNotes;
    private String qsarReadySmiles;
    private String msReadySmiles;
    private String irisLink;
    private String pprtvLink;
    private String wikipediaArticle;
    private String descriptorStringTsv;
    private Date dateLoaded;
    private String hchemHashKey;
}
