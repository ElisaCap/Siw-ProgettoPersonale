package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.ElementoClassifica;
import jakarta.transaction.Transactional;

public interface ElementoClassificaRepository extends CrudRepository<ElementoClassifica,Long> {
	@Transactional
public Iterable<ElementoClassifica>findAllByClassificaOrderByPosizione(Classifica classifica);
    @Modifying
    @Transactional
    @Query("UPDATE ElementoClassifica e SET e.posizione = e.posizione + 1 WHERE e.posizione >= :posizione")
  public  void incrementaPosizioniDa(int posizione);
}
	
	

