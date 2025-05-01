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
		    // Edizione 1: Palio del 2 luglio 2022 (solo 4 contrade arrivate)
		    // 1° Drago
			
		    this.aggiungiElementoClassifica(classificaRepository.findById(1L).get(),1,
		        partecipazioneRepository.findById(5L).get());
		    // 2° Torre
		    this.aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 2,
		        partecipazioneRepository.findById(16L).get());
		    // 3° Pantera
		    this.aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 3,
		        partecipazioneRepository.findById(13L).get());
		    // 4° Lupa
		    this.aggiungiElementoClassifica(classificaRepository.findById(1L).get(), 4,
		        partecipazioneRepository.findById(9L).get());

		    // Edizione 2: Palio del 16 agosto 2022
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 1,
		        partecipazioneRepository.findById(9L).get());   // Leocorno
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 2,
		        partecipazioneRepository.findById(5L).get());   // Selva
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 3,
		        partecipazioneRepository.findById(1L).get());   // Valdimontone
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 4,
		        partecipazioneRepository.findById(3L).get());   // Giraffa
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 5,
		        partecipazioneRepository.findById(2L).get());   // Chiocciola
		  
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 6,
		        partecipazioneRepository.findById(6L).get());   // Tartuca
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 7,
		        partecipazioneRepository.findById(10L).get());  // Nicchio
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 8,
		        partecipazioneRepository.findById(4L).get());   // Lupa
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 9,
		        partecipazioneRepository.findById(8L).get());   // Onda
		    this.aggiungiElementoClassifica(classificaRepository.findById(2L).get(), 10,
		        partecipazioneRepository.findById(7L).get());   // Civetta

		    // Edizione 3: Palio del 2 luglio 2023
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 1,
		        partecipazioneRepository.findById(34L).get());  // Selva
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 2,
		        partecipazioneRepository.findById(40L).get());  // Torre
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 3,
		        partecipazioneRepository.findById(36L).get());  // Giraffa
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 4,
		        partecipazioneRepository.findById(39L).get());  // Drago
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 5,
		        partecipazioneRepository.findById(38L).get());  // Onda
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 6,
		        partecipazioneRepository.findById(41L).get());  // Nicchio
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 7,
		        partecipazioneRepository.findById(42L).get());  // Aquila
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 8,
		        partecipazioneRepository.findById(33L).get());  // Tartuca
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 9,
		        partecipazioneRepository.findById(35L).get());  // Chiocciola
		    this.aggiungiElementoClassifica(classificaRepository.findById(3L).get(), 10,
		        partecipazioneRepository.findById(37L).get());  // Istrice
		}

	
	
	

}
