package it.uniroma3.siw.service;
import it.uniroma3.siw.repository.PartecipazioneRepository;
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
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 1, partecipazioneRepository.findById(5L).get());   // Drago
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 2, partecipazioneRepository.findById(16L).get()); // Torre
	    aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 3, partecipazioneRepository.findById(13L).get()); // Pantera

	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 1, partecipazioneRepository.findById(9L).get());   // Leocorno

	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 3, partecipazioneRepository.findById(1L).get());   // Valdimontone
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 4, partecipazioneRepository.findById(3L).get());   // Giraffa
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 5, partecipazioneRepository.findById(2L).get());   // Chiocciola
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 6, partecipazioneRepository.findById(6L).get());   // Tartuca
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 7, partecipazioneRepository.findById(10L).get());  // Nicchio
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 8, partecipazioneRepository.findById(4L).get());   // Lupa
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 9, partecipazioneRepository.findById(8L).get());   // Onda
	    aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 10, partecipazioneRepository.findById(7L).get());  // Civetta

	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 1, partecipazioneRepository.findById(34L).get());  // Selva
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 2, partecipazioneRepository.findById(40L).get());  // Torre
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 3, partecipazioneRepository.findById(36L).get());  // Giraffa
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 4, partecipazioneRepository.findById(39L).get());  // Drago
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 5, partecipazioneRepository.findById(38L).get());  // Onda
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 6, partecipazioneRepository.findById(41L).get());  // Nicchio
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 7, partecipazioneRepository.findById(42L).get());  // Aquila
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 8, partecipazioneRepository.findById(33L).get());  // Tartuca
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 9, partecipazioneRepository.findById(35L).get());  // Chiocciola
	    aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 10, partecipazioneRepository.findById(37L).get()); // Istrice
	}

	
	public void deleteById(Long id) {
	    this.elementoClassificaRepository.deleteById(id);
	}
	

}
