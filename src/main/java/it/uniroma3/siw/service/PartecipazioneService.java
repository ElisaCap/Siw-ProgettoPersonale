package it.uniroma3.siw.service;

import java.beans.Transient;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.model.Partecipazione;
import it.uniroma3.siw.repository.CavalloRepository;
import it.uniroma3.siw.repository.ContradaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.FantinoRepository;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

@Service
public class PartecipazioneService {
@Autowired
    private  EdizioneRepository edizioneRepository;
@Autowired
    private  CavalloRepository cavalloRepository;
@Autowired
    private  FantinoRepository fantinoRepository;
@Autowired
    private  ContradaRepository contradaRepository;
	@Autowired
private PartecipazioneRepository partecipazioneRepository;

    PartecipazioneService(ContradaRepository contradaRepository, FantinoRepository fantinoRepository,
    		CavalloRepository cavalloRepository,EdizioneRepository edizioneRepository) {
        this.contradaRepository = contradaRepository;
        this.fantinoRepository = fantinoRepository;
        this.cavalloRepository = cavalloRepository;
        this.edizioneRepository = edizioneRepository;
    }
    @Transactional
	public Partecipazione getById(Long id) {
		return this.partecipazioneRepository.findById(id).get();
	}
	private Contrada contrada;
	@ManyToOne
	private Fantino fantino;
	@ManyToOne
	private Cavallo cavallo;
	@ManyToOne
	private Edizione edizione;


	public void aggiungiPartecizpazione(Contrada contrada,Fantino fantino,Cavallo cavallo,Edizione edizione) {
	Partecipazione partecipazione=new Partecipazione();
		partecipazione.setContrada(contrada);
		partecipazione.setCavallo(cavallo);
		partecipazione.setFantino(fantino);
		partecipazione.setEdizione(edizione);
		this.partecipazioneRepository.save(partecipazione);
	}
	
	public void inizializza() {
	    int[][] partecipazioni = {
	        // Edizione 1 (luglio 2022)
	        {1, 1, 1, 1}, {2, 2, 2, 1}, {3, 3, 3, 1}, {4, 4, 4, 1}, {5, 5, 5, 1}, {6, 6, 6, 1},
	        {7, 7, 7, 1}, {8, 8, 8, 1}, {9, 9, 9, 1}, {10, 10, 10, 1}, {11, 11, 11, 1}, {12, 12, 12, 1},
	        {13, 13, 13, 1}, {14, 14, 14, 1}, {15, 1, 1, 1}, {16, 2, 2, 1}, {17, 3, 3, 1},

	        // Edizione 2 (agosto 2022)
	        {1, 2, 2, 2}, {2, 3, 3, 2}, {3, 4, 4, 2}, {4, 5, 5, 2}, {5, 6, 6, 2}, {6, 7, 7, 2},
	        {7, 8, 8, 2}, {8, 9, 9, 2}, {9, 10, 10, 2}, {10, 11, 11, 2}, {11, 12, 12, 2}, {12, 13, 13, 2},
	        {13, 14, 14, 2}, {14, 1, 1, 2}, {15, 2, 2, 2}, {16, 3, 3, 2}, {17, 4, 4, 2},

	        // Edizione 3 (luglio 2023)
	        {1, 3, 3, 3}, {2, 4, 4, 3}, {3, 5, 5, 3}, {4, 6, 6, 3}, {5, 7, 7, 3}, {6, 8, 8, 3},
	        {7, 9, 9, 3}, {8, 10, 10, 3}, {9, 11, 11, 3}, {10, 12, 12, 3}, {11, 13, 13, 3}, {12, 14, 14, 3},
	        {13, 1, 1, 3}, {14, 2, 2, 3}, {15, 3, 3, 3}, {16, 4, 4, 3}, {17, 5, 5, 3}
	    };

	    for (int[] p : partecipazioni) {
	        Contrada c = contradaRepository.findById((long) p[0]).orElse(null);
	        Fantino f = fantinoRepository.findById((long) p[1]).orElse(null);
	        Cavallo cav = cavalloRepository.findById((long) p[2]).orElse(null);
	        Edizione e = edizioneRepository.findById((long) p[3]).orElse(null);

	        if (c != null && f != null && cav != null && e != null) {
	            Partecipazione partecipazione = new Partecipazione();
	            partecipazione.setContrada(c);
	            partecipazione.setFantino(f);
	            partecipazione.setCavallo(cav);
	            partecipazione.setEdizione(e);
	            partecipazioneRepository.save(partecipazione);
	        }
	    }
	}

public void save(Partecipazione partecipazione) {
	this.partecipazioneRepository.save(partecipazione);
}
	







public Iterable<Partecipazione>getByCavallo(Cavallo cavallo){
	return this.partecipazioneRepository.findByCavallo(cavallo);
}



public void deleteById(Long id) {
    this.partecipazioneRepository.deleteById(id);
}


public Iterable<Partecipazione>getAll(){
	return this.partecipazioneRepository.findAll();
}
public Iterable<Partecipazione> getByFantino(Fantino fantino) {
	// TODO Auto-generated method stub
	return partecipazioneRepository.findByFantino(fantino);
}
public Iterable<Partecipazione> getByContrada(Contrada contrada) {
	// TODO Auto-generated method stub
	return partecipazioneRepository.findByContrada(contrada);
}
public Iterable<Partecipazione> findWhereCavalloPrimo(Cavallo cavallo){
	return this.partecipazioneRepository.findWhereCavalloPrimo(cavallo);
}


}
