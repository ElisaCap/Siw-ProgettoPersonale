package it.uniroma3.siw.repository;

import java.beans.Transient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentoRepository extends CrudRepository<Commento, Long> {
@Transactional
public Iterable<Commento> findByEdizione(Edizione edizione);
}
