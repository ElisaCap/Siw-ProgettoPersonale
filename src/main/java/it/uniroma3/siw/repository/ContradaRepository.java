package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Contrada;
import jakarta.transaction.Transactional;

public interface ContradaRepository extends CrudRepository<Contrada, Long> {
    @Transactional
public Contrada findByNome(String nome);
@Modifying
@Transactional
@Query(value = "DELETE FROM contrada_contrade_rivali WHERE (contrada_id = :id AND contrade_rivali_id = :idc) OR (contrada_id = :idc AND contrade_rivali_id = :id)", nativeQuery = true)
void deleteRivali(@Param("id") Long id, @Param("idc") Long idc);

}
