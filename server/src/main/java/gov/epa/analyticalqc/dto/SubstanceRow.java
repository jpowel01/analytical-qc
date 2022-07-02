package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.Call;
import gov.epa.analyticalqc.entity.Grade;
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

    private Grade t0Grade;

    private Grade t4Grade;

    private Call call;

    private Boolean validated;
    
}
