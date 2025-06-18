package it.uniroma3.siw.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Edizione;
import jakarta.transaction.Transactional;

public interface EdizioneRepository extends CrudRepository<Edizione,Long> {
	@Transactional
public Edizione findByData(LocalDate data);
@Transactional
@Query("SELECT e FROM Edizione e " +  // <-- intera entità Edizione
	       "JOIN e.partecipazioni p " +
	       "JOIN p.cavallo c " +
	       "WHERE c.id = :cavalloId " +
	       "ORDER BY e.id")
	public Iterable<Edizione> findEdizioniByCavalloId(@Param("cavalloId") Long cavalloId);




}
