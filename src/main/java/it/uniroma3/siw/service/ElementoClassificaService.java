package it.uniroma3.siw.service;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.model.Partecipazione;
import it.uniroma3.siw.repository.ClassificaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;

@Service
public class ElementoClassificaService {

    private final EdizioneRepository edizioneRepository;

    private final ClassificaRepository classificaRepository;

    private final PartecipazioneRepository partecipazioneRepository;
	@Autowired
	private ElementoClassificaRepository elementoClassificaRepository;

    ElementoClassificaService(PartecipazioneRepository partecipazioneRepository, ClassificaRepository classificaRepository, EdizioneRepository edizioneRepository) {
        this.partecipazioneRepository = partecipazioneRepository;
        this.classificaRepository = classificaRepository;
        this.edizioneRepository = edizioneRepository;
    }
	private void aggiungiElementoClassifica(Classifica classifica,int posizione,Partecipazione partecipazione ) {
		ElementoClassifica elementoClassifica=new ElementoClassifica();
		elementoClassifica.setClassifica(classifica);
		elementoClassifica.setPosizione(posizione);
		elementoClassifica.setPartecipazione(partecipazione);
		this.elementoClassificaRepository.save(elementoClassifica);
	}
	
	
	public void inizializza() {
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 6, partecipazioneRepository.findById(1L).get());   
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 1, partecipazioneRepository.findById(2L).get());
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 3, partecipazioneRepository.findById(5L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 2, partecipazioneRepository.findById(4L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 4, partecipazioneRepository.findById(3L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 5, partecipazioneRepository.findById(10L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 7, partecipazioneRepository.findById(8L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 8, partecipazioneRepository.findById(6L).get());
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 9, partecipazioneRepository.findById(9L).get()); 
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 10, partecipazioneRepository.findById(7L).get()); 






	}

	

	@Transactional
	public void deleteById(Long id) {
	    Optional<ElementoClassifica> elemento = elementoClassificaRepository.findById(id);
	    if (elemento.isPresent()) {
	    	
	        ElementoClassifica e = elemento.get();
	        elementoClassificaRepository.derementaPosizioniDa(e.getPosizione(),e.getClassifica().getEdizioneId());
	        // Disassocia da classifica e partecipazione
	        e.setClassifica(null);
	        e.setPartecipazione(null);
	        elementoClassificaRepository.save(e); // salva la disassociazione
	        elementoClassificaRepository.delete(e);// ora elimina
	       
	    }
	}


	

}
