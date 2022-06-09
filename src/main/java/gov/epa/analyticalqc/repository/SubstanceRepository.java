package gov.epa.analyticalqc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Substance;

public interface SubstanceRepository extends JpaRepository<Substance, Integer> {

    Optional<Substance> findById(Integer id);

    Optional<Substance> findByDtxsid(String dtxsid);

    Optional<Substance> findByCasrn(String casrn);

    List<Substance> findByMolFormula(String molFormula);

    List<Substance> findByPreferredNameContaining(String query);

}