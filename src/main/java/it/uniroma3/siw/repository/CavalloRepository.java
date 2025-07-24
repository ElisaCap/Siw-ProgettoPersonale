package it.uniroma3.siw.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Cavallo;
import jakarta.transaction.Transactional;
@Repository
public interface CavalloRepository extends CrudRepository<Cavallo, Long> {
	@Transactional
public Cavallo findByNome(String nome);
@Transactional
	public Iterable<Cavallo> findAllByNome(String nome);
}
