package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Fantino;

public interface FantinoRepository extends CrudRepository<Fantino,Long> {
	public Fantino findByNome(String nome);
}
