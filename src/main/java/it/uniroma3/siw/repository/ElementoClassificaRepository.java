package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.ElementoClassifica;
import jakarta.transaction.Transactional;

public interface ElementoClassificaRepository extends CrudRepository<ElementoClassifica,Long> {
	@Transactional
public Iterable<ElementoClassifica>findAllByClassifica(Classifica classifica);
}
