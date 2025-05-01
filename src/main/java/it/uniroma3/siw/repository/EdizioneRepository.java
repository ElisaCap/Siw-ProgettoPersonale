package it.uniroma3.siw.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Edizione;

public interface EdizioneRepository extends CrudRepository<Edizione,Long> {
public Edizione findByData(LocalDate data);
}
