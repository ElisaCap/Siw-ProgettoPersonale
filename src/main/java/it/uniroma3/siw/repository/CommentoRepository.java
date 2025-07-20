package it.uniroma3.siw.repository;

import java.beans.Transient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.User;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentoRepository extends CrudRepository<Commento, Long> {
@Transactional
public Iterable<Commento> findByEdizione(Edizione edizione);
@Transactional
public void deleteById(Long id);
@Transactional
public Iterable<Commento> findAllByUser(User user);
@Transactional
public List<Commento> findByUserAndEdizione(User user, Edizione edizione);
}
