package gov.epa.analyticalqc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Object[]> getSingleResult(String id) {
        String HQL = "select s.id as id, s.dtxsid as DTXSID, s.casrn as CASRN, s.preferred_name as Preferred_Name, " +
                "g.name as T0, g2.name as t4," +
                "c.name as call from sbox_analytical_qc.substances s " +
                "left join sbox_analytical_qc.substance_annotations sa on s.id = sa.substance_id " +
                "left join sbox_analytical_qc.grades g on g.id = sa.t0_grade_id " +
                "left join sbox_analytical_qc.grades g2 on g.id = sa.t4_grade_id " +
                "left join sbox_analytical_qc.calls c on c.id = sa.call_id " +
                " where s.dtxsid = :dtxsid";
        Query q = (Query) entityManager.createNativeQuery(HQL);
        q.setParameter("dtxsid", id);
        List<Object[]> results = q.getResultList();
        return results;
    }

    public List<Object[]> getAllResult(List<String> id) {
        String HQL = "select s.id as id, s.dtxsid as DTXSID, s.casrn as CASRN, s.preferred_name as Preferred_Name, " +
                "g.name as T0, g2.name as t4," +
                "c.name as call from sbox_analytical_qc.substances s " +
                "left join sbox_analytical_qc.substance_annotations sa on s.id = sa.substance_id " +
                "left join sbox_analytical_qc.grades g on g.id = sa.t0_grade_id " +
                "left join sbox_analytical_qc.grades g2 on g2.id = sa.t4_grade_id " +
                "left join sbox_analytical_qc.calls c on c.id = sa.call_id " +
                " where s.dtxsid in (:dtxsids)";
        Query q = (Query) entityManager.createNativeQuery(HQL);
        q.setParameter("dtxsids", id);
        List<Object[]> results = q.getResultList();
        return results;
    }



    /*

                s.setId((Integer) item[0]);
            s.setDtxsid(String.valueOf(item[1]));
            s.setPreferredName(String.valueOf(item[2]));
            s.setMolFormula(String.valueOf(item[3]));
            s.setMolWeight((Double) item[3]);
            s.setPubchemCid((Long) item[3]);



     */
}
