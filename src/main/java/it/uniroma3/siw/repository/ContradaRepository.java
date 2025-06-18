package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Contrada;
import jakarta.transaction.Transactional;

public interface ContradaRepository extends CrudRepository<Contrada, Long> {
    @Transactional
public Contrada findByNome(String nome);

}
