package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Contrada;

public interface ContradaRepository extends CrudRepository<Contrada, Long> {
public Contrada findByNome(String nome);
}
