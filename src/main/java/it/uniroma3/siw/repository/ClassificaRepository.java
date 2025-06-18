package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import jakarta.transaction.Transactional;

public interface ClassificaRepository extends CrudRepository<Classifica,Long > {
	@Transactional
	Optional<Classifica> findByEdizione(Edizione edizione);

}
