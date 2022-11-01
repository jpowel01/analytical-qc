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
    
    public DashboardResponse() {
    }
    
	public DashboardResponse(Integer id, String dtxsid, String dtxcid, String casrn, Integer compoundId,
			Integer genericSubstanceId, String preferredName, Integer activeAssays, Long cpdataCount, String molFormula,
			Double monoisotopicMass, Double percentAssays, Integer pubchemCount, Double pubmedCount, Long sourcesCount,
			Integer qcLevel, String qcLevelDesc, String stereo, Integer isotope, Integer multicomponent,
			Integer totalAssays, String toxcastSelect, Integer pubchemCid, String molFile, String mrvFile,
			Long relatedSubstanceCount, Long relatedStructureCount, Integer hasStructureImage, String iupacName,
			String smiles, String inchiString, Double averageMass, String inchikey, String qcNotes,
			String qsarReadySmiles, String msReadySmiles, String irisLink, String pprtvLink, String wikipediaArticle,
			String descriptorStringTsv, Date dateLoaded, String hchemHashKey) {
		super();
		this.id = id;
		this.dtxsid = dtxsid;
		this.dtxcid = dtxcid;
		this.casrn = casrn;
		this.compoundId = compoundId;
		this.genericSubstanceId = genericSubstanceId;
		this.preferredName = preferredName;
		this.activeAssays = activeAssays;
		this.cpdataCount = cpdataCount;
		this.molFormula = molFormula;
		this.monoisotopicMass = monoisotopicMass;
		this.percentAssays = percentAssays;
		this.pubchemCount = pubchemCount;
		this.pubmedCount = pubmedCount;
		this.sourcesCount = sourcesCount;
		this.qcLevel = qcLevel;
		this.qcLevelDesc = qcLevelDesc;
		this.stereo = stereo;
		this.isotope = isotope;
		this.multicomponent = multicomponent;
		this.totalAssays = totalAssays;
		this.toxcastSelect = toxcastSelect;
		this.pubchemCid = pubchemCid;
		this.molFile = molFile;
		this.mrvFile = mrvFile;
		this.relatedSubstanceCount = relatedSubstanceCount;
		this.relatedStructureCount = relatedStructureCount;
		this.hasStructureImage = hasStructureImage;
		this.iupacName = iupacName;
		this.smiles = smiles;
		this.inchiString = inchiString;
		this.averageMass = averageMass;
		this.inchikey = inchikey;
		this.qcNotes = qcNotes;
		this.qsarReadySmiles = qsarReadySmiles;
		this.msReadySmiles = msReadySmiles;
		this.irisLink = irisLink;
		this.pprtvLink = pprtvLink;
		this.wikipediaArticle = wikipediaArticle;
		this.descriptorStringTsv = descriptorStringTsv;
		this.dateLoaded = dateLoaded;
		this.hchemHashKey = hchemHashKey;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDtxsid() {
		return dtxsid;
	}
	public void setDtxsid(String dtxsid) {
		this.dtxsid = dtxsid;
	}
	public String getDtxcid() {
		return dtxcid;
	}
	public void setDtxcid(String dtxcid) {
		this.dtxcid = dtxcid;
	}
	public String getCasrn() {
		return casrn;
	}
	public void setCasrn(String casrn) {
		this.casrn = casrn;
	}
	public Integer getCompoundId() {
		return compoundId;
	}
	public void setCompoundId(Integer compoundId) {
		this.compoundId = compoundId;
	}
	public Integer getGenericSubstanceId() {
		return genericSubstanceId;
	}
	public void setGenericSubstanceId(Integer genericSubstanceId) {
		this.genericSubstanceId = genericSubstanceId;
	}
	public String getPreferredName() {
		return preferredName;
	}
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	public Integer getActiveAssays() {
		return activeAssays;
	}
	public void setActiveAssays(Integer activeAssays) {
		this.activeAssays = activeAssays;
	}
	public Long getCpdataCount() {
		return cpdataCount;
	}
	public void setCpdataCount(Long cpdataCount) {
		this.cpdataCount = cpdataCount;
	}
	public String getMolFormula() {
		return molFormula;
	}
	public void setMolFormula(String molFormula) {
		this.molFormula = molFormula;
	}
	public Double getMonoisotopicMass() {
		return monoisotopicMass;
	}
	public void setMonoisotopicMass(Double monoisotopicMass) {
		this.monoisotopicMass = monoisotopicMass;
	}
	public Double getPercentAssays() {
		return percentAssays;
	}
	public void setPercentAssays(Double percentAssays) {
		this.percentAssays = percentAssays;
	}
	public Integer getPubchemCount() {
		return pubchemCount;
	}
	public void setPubchemCount(Integer pubchemCount) {
		this.pubchemCount = pubchemCount;
	}
	public Double getPubmedCount() {
		return pubmedCount;
	}
	public void setPubmedCount(Double pubmedCount) {
		this.pubmedCount = pubmedCount;
	}
	public Long getSourcesCount() {
		return sourcesCount;
	}
	public void setSourcesCount(Long sourcesCount) {
		this.sourcesCount = sourcesCount;
	}
	public Integer getQcLevel() {
		return qcLevel;
	}
	public void setQcLevel(Integer qcLevel) {
		this.qcLevel = qcLevel;
	}
	public String getQcLevelDesc() {
		return qcLevelDesc;
	}
	public void setQcLevelDesc(String qcLevelDesc) {
		this.qcLevelDesc = qcLevelDesc;
	}
	public String getStereo() {
		return stereo;
	}
	public void setStereo(String stereo) {
		this.stereo = stereo;
	}
	public Integer getIsotope() {
		return isotope;
	}
	public void setIsotope(Integer isotope) {
		this.isotope = isotope;
	}
	public Integer getMulticomponent() {
		return multicomponent;
	}
	public void setMulticomponent(Integer multicomponent) {
		this.multicomponent = multicomponent;
	}
	public Integer getTotalAssays() {
		return totalAssays;
	}
	public void setTotalAssays(Integer totalAssays) {
		this.totalAssays = totalAssays;
	}
	public String getToxcastSelect() {
		return toxcastSelect;
	}
	public void setToxcastSelect(String toxcastSelect) {
		this.toxcastSelect = toxcastSelect;
	}
	public Integer getPubchemCid() {
		return pubchemCid;
	}
	public void setPubchemCid(Integer pubchemCid) {
		this.pubchemCid = pubchemCid;
	}
	public String getMolFile() {
		return molFile;
	}
	public void setMolFile(String molFile) {
		this.molFile = molFile;
	}
	public String getMrvFile() {
		return mrvFile;
	}
	public void setMrvFile(String mrvFile) {
		this.mrvFile = mrvFile;
	}
	public Long getRelatedSubstanceCount() {
		return relatedSubstanceCount;
	}
	public void setRelatedSubstanceCount(Long relatedSubstanceCount) {
		this.relatedSubstanceCount = relatedSubstanceCount;
	}
	public Long getRelatedStructureCount() {
		return relatedStructureCount;
	}
	public void setRelatedStructureCount(Long relatedStructureCount) {
		this.relatedStructureCount = relatedStructureCount;
	}
	public Integer getHasStructureImage() {
		return hasStructureImage;
	}
	public void setHasStructureImage(Integer hasStructureImage) {
		this.hasStructureImage = hasStructureImage;
	}
	public String getIupacName() {
		return iupacName;
	}
	public void setIupacName(String iupacName) {
		this.iupacName = iupacName;
	}
	public String getSmiles() {
		return smiles;
	}
	public void setSmiles(String smiles) {
		this.smiles = smiles;
	}
	public String getInchiString() {
		return inchiString;
	}
	public void setInchiString(String inchiString) {
		this.inchiString = inchiString;
	}
	public Double getAverageMass() {
		return averageMass;
	}
	public void setAverageMass(Double averageMass) {
		this.averageMass = averageMass;
	}
	public String getInchikey() {
		return inchikey;
	}
	public void setInchikey(String inchikey) {
		this.inchikey = inchikey;
	}
	public String getQcNotes() {
		return qcNotes;
	}
	public void setQcNotes(String qcNotes) {
		this.qcNotes = qcNotes;
	}
	public String getQsarReadySmiles() {
		return qsarReadySmiles;
	}
	public void setQsarReadySmiles(String qsarReadySmiles) {
		this.qsarReadySmiles = qsarReadySmiles;
	}
	public String getMsReadySmiles() {
		return msReadySmiles;
	}
	public void setMsReadySmiles(String msReadySmiles) {
		this.msReadySmiles = msReadySmiles;
	}
	public String getIrisLink() {
		return irisLink;
	}
	public void setIrisLink(String irisLink) {
		this.irisLink = irisLink;
	}
	public String getPprtvLink() {
		return pprtvLink;
	}
	public void setPprtvLink(String pprtvLink) {
		this.pprtvLink = pprtvLink;
	}
	public String getWikipediaArticle() {
		return wikipediaArticle;
	}
	public void setWikipediaArticle(String wikipediaArticle) {
		this.wikipediaArticle = wikipediaArticle;
	}
	public String getDescriptorStringTsv() {
		return descriptorStringTsv;
	}
	public void setDescriptorStringTsv(String descriptorStringTsv) {
		this.descriptorStringTsv = descriptorStringTsv;
	}
	public Date getDateLoaded() {
		return dateLoaded;
	}
	public void setDateLoaded(Date dateLoaded) {
		this.dateLoaded = dateLoaded;
	}
	public String getHchemHashKey() {
		return hchemHashKey;
	}
	public void setHchemHashKey(String hchemHashKey) {
		this.hchemHashKey = hchemHashKey;
	}
    
    

}


