package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.Call;

public interface CallRepository extends JpaRepository<Call, Integer> {

    Optional<Call> findById(Integer id);

}