package gov.epa.analyticalqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.epa.analyticalqc.entity.ListSubstance;

public interface ListSubstanceRepository extends JpaRepository<ListSubstance, Integer> {

    @Query("select ls.substance.id from ListSubstance ls where ls.list.id = :listId order by ls.substance.id asc")
    List<Integer> findSubstanceIdsByListId(Integer listId);

}