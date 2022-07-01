package gov.epa.analyticalqc.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.Substance;

public interface SubstanceRepository extends JpaRepository<Substance, Integer> {

    Optional<Substance> findById(Integer id);

    Optional<Substance> findByDtxsid(String dtxsid);

    Optional<Substance> findByCasrn(String casrn);

    Page<Substance> findByMolFormula(String molFormula, Pageable pageable);

    Page<Substance> findByPreferredNameContainingIgnoreCase(String query, Pageable pageable);

    Page<Substance> findByIdIn(Collection<Integer> ids, Pageable pageable);

    List<Substance> findByIdIn(Collection<Integer> ids);

    List<Substance> findByDtxsidIn(Collection<String> dtxsid);

    List<Substance> findByMolWeightGreaterThan(Double min);

    List<Substance> findByMolWeightLessThan(Double max);

    List<Substance> findByMolWeightBetween(Double min, Double max);

}