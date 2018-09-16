package com.rflpazini.xgroup.repository;

import com.rflpazini.xgroup.model.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
    @Query(value = "SELECT COUNT(id) FROM mutants WHERE type = ?1", nativeQuery = true)
    Long countMutantsBy(int type);

    @Query("SELECT m FROM Mutant m WHERE m.dna = ?1")
    Mutant findMutantByDna(String dna);
}
