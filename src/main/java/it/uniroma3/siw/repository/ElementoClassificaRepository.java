package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.ElementoClassifica;

public interface ElementoClassificaRepository extends CrudRepository<ElementoClassifica,Long> {
public Iterable<ElementoClassifica>findAllByClassifica(Classifica classifica);
}
