package gov.epa.analyticalqc.dto;

import gov.epa.analyticalqc.entity.Sample;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceAnnotation;
import gov.epa.analyticalqc.entity.Grade;
import lombok.*;


/*
[4:10 PM] Ramsland, Christian
select s.dtxsid as DTXSID, s.casrn as CASRN, s.preferred_name as Preferred_Name, g."name" as T0, g2."name" as t4,
c."name" as call,
ap.lcms_amen_neg as "LCMS (ESI-) Amenity Prediction", ap.lcms_amen_pos as "LCMS (ESI+) Amenity Prediction",
ap.lcms_amen_neg_true as "LCMS (ESI-) Amenity Predicted True", ap.lcms_amen_pos_true as "LCMS (ESI+) Amenity Predicted True",
case when sf.name = 'INORG' then 'INORG'    else null end as Inorganic,
case when sf.name = 'UVCB' then 'UVCB'    else null end as UVCB
from sbox_analytical_qc.substances s
left join sbox_analytical_qc.substance_annotations sa on s.id = sa.substance_id
left join sbox_analytical_qc.grades g on g.id = sa.t0_grade_id
left join sbox_analytical_qc.grades g2 on g.id = sa.t4_grade_id
left join sbox_analytical_qc.calls c on c.id = sa.call_id
left join sbox_analytical_qc.amenability_predictions ap on ap.substance_id = s.id
left join sbox_analytical_qc.substance_structure_flags ssf on ssf.substance_id = s.id
left join sbox_analytical_qc.structure_flags sf on sf.id = ssf.structure_flag_id
where dtxsid = 'DTXSID6045743'

 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExcelDownloadDto {

 //   private Substance substance;

    private Integer id;

    private String dtxsid;
    private String preferredName;

    private String casrn;

    private String T0;

    private String T4;

    private String call;



    // private SubstanceAnnotation substanceAnnotation;

    // private Grade grade;
    /*

    private String T0;

    private String T4;

    private String call;

    private Double lcms_amen_neg;

    private Double lcms_amen_pos;

    private Boolean lcms_amen_neg_true;

    private Boolean lcms_amen_pos_true;

    private String inorganic;

    private String UVCB;

     */

}
