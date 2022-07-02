package gov.epa.analyticalqc.dto;

import lombok.Value;

@Value
public class SubstanceRow {

    private Integer id;

    private String dtxsid;

    private String preferredName;

    private String casrn;

    private String molFormula;

    private Double molWeight;

    private Long pubchemCid;

    private String t0Grade;

    private String t4Grade;

    private String call;

    private Boolean validated;
    
}
