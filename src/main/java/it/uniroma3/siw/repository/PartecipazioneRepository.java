package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.Partecipazione;
import jakarta.transaction.Transactional;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione,Long> {
	@Transactional
	@Query("SELECT p FROM Partecipazione p WHERE p.edizione.id = :edizioneId")
	List<Partecipazione> findByEdizioneId(@Param("edizioneId") Long edizioneId);



}
