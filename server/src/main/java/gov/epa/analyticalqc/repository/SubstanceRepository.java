package gov.epa.analyticalqc.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.dto.SubstanceRow;
import gov.epa.analyticalqc.entity.Substance;

public interface SubstanceRepository extends JpaRepository<Substance, Integer> {

    Optional<Substance> findById(Integer id);

    Optional<Substance> findByDtxsid(String dtxsid);

    Optional<Substance> findByCasrn(String casrn);

    Page<Substance> findByMolFormula(String molFormula, Pageable pageable);

    @Query("select new gov.epa.analyticalqc.dto.SubstanceRow(s.id, s.dtxsid, s.preferredName, s.casrn, s.molFormula, s.molWeight, s.pubchemCid, "
        + "sa.t0Grade, sa.t4Grade, sa.call, sa.validated) from Substance s left join s.substanceAnnotation sa left join sa.call left join sa.t0Grade left join sa.t4Grade "
        + "where s.preferredName like LOWER('%' || :query || '%') order by s.id asc")
    Page<SubstanceRow> findRowsByPreferredNameContainingIgnoreCase(String query, Pageable pageable);

    @Query("select new gov.epa.analyticalqc.dto.SubstanceRow(s.id, s.dtxsid, s.preferredName, s.casrn, s.molFormula, s.molWeight, s.pubchemCid, "
        + "sa.t0Grade, sa.t4Grade, sa.call, sa.validated) from Substance s left join s.substanceAnnotation sa left join sa.call left join sa.t0Grade left join sa.t4Grade "
        + "join ListSubstance ls on ls.substance.id = s.id and ls.list.id = :listId "
        + "where s.preferredName like LOWER('%' || :query || '%') order by s.id asc")
    Page<SubstanceRow> findRowsByListIdAndPreferredNameContainingIgnoreCase(String query, Integer listId, Pageable pageable);

    @Query("select new gov.epa.analyticalqc.dto.SubstanceRow(s.id, s.dtxsid, s.preferredName, s.casrn, s.molFormula, s.molWeight, s.pubchemCid, "
        + "sa.t0Grade, sa.t4Grade, sa.call, sa.validated) from Substance s left join s.substanceAnnotation sa left join sa.call left join sa.t0Grade left join sa.t4Grade "
        + "join ListSubstance ls on ls.substance.id = s.id and ls.list.id = :listId order by s.id asc")
    Page<SubstanceRow> findRowsByListId(Integer listId, Pageable pageable);

    @Query("select new gov.epa.analyticalqc.dto.SubstanceRow(s.id, s.dtxsid, s.preferredName, s.casrn, s.molFormula, s.molWeight, s.pubchemCid, "
        + "sa.t0Grade, sa.t4Grade, sa.call, sa.validated) from Substance s left join s.substanceAnnotation sa left join sa.call left join sa.t0Grade left join sa.t4Grade order by s.id asc")
    Page<SubstanceRow> findAllRows(Pageable pageable);

    Page<Substance> findByIdIn(Collection<Integer> ids, Pageable pageable);

    List<Substance> findByIdIn(Collection<Integer> ids);

    List<Substance> findByDtxsidIn(Collection<String> dtxsid);

    List<Substance> findByMolWeightGreaterThan(Double min);

    List<Substance> findByMolWeightLessThan(Double max);

    List<Substance> findByMolWeightBetween(Double min, Double max);

}