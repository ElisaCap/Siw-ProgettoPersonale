package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.Partecipazione;
import jakarta.transaction.Transactional;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione,Long> {
	@Transactional
public Iterable<Partecipazione> findAllByEdizione(Edizione edizione);
}
