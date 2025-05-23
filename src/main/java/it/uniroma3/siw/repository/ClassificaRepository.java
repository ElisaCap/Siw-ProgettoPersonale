package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;

public interface ClassificaRepository extends CrudRepository<Classifica,Long > {
	Optional<Classifica> findByEdizione(Edizione edizione);

}
