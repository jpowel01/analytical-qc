package gov.epa.analyticalqc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.epa.analyticalqc.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

    Optional<File> findByFileName(String fileName);

}