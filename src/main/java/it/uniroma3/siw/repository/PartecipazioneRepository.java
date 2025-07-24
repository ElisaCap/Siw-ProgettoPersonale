package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.model.Partecipazione;
import jakarta.transaction.Transactional;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione,Long> {
	@Transactional
	@Query("SELECT p FROM Partecipazione p WHERE p.edizione.id = :edizioneId AND p.elementoClassifica IS NULL")
	List<Partecipazione> findByEdizioneId(@Param("edizioneId") Long edizioneId);

@Transactional
public Iterable<Partecipazione> findByCavallo(Cavallo cavallo);
@Transactional
public Iterable<Partecipazione> findByFantino(Fantino fantino);
@Transactional
public Iterable<Partecipazione> findByContrada(Contrada contrada);
@Transactional
@Query("SELECT e.partecipazione FROM ElementoClassifica e " +
       "WHERE e.partecipazione.cavallo = :cav AND e.posizione = 1")
Iterable<Partecipazione> findWhereCavalloPrimo(@Param("cav") Cavallo cavallo);
@Transactional
@Query("SELECT p.cavallo FROM Partecipazione p " +
	       "WHERE p.edizione.id= :id ")
	Iterable<Cavallo> findAllCavalli(@Param("id") Long id);

@Transactional
@Query("SELECT p.fantino FROM Partecipazione p " +
	       "WHERE p.edizione.id= :id ")
	Iterable<Fantino> findAllFantini(@Param("id") Long id);


@Transactional
@Query("SELECT p.contrada FROM Partecipazione p " +
	       "WHERE p.edizione.id= :id ")
	Iterable<Contrada> findAllContrade(@Param("id") Long id);



}
