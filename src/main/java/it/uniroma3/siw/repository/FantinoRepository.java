package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Fantino;
import jakarta.transaction.Transactional;

public interface FantinoRepository extends CrudRepository<Fantino,Long> {
	@Transactional
	public Fantino findByNome(String nome);
	@Transactional
	Iterable<Fantino> findAllByOrderByCognomeAsc();

}
