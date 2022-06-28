package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Substance;

public interface SubstanceRepository extends JpaRepository<Substance, Integer> {

    Optional<Substance> findById(Integer id);

    Optional<Substance> findByDtxsid(String dtxsid);

    Optional<Substance> findByCasrn(String casrn);

    Page<Substance> findByMolFormula(String molFormula, Pageable pageable);

    Page<Substance> findByPreferredNameContainingIgnoreCase(String query, Pageable pageable);

    Page<Substance> findByIdIn(List<Integer> ids, Pageable pageable);

}