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

	
	public void aggiungiPartecizpazione(Cavallo cavallo,Fantino fantino,Contrada contrada,Edizione edizione) {
	Partecipazione partecipazione=new Partecipazione();
		partecipazione.setContrada(contrada);
		partecipazione.setCavallo(cavallo);
		partecipazione.setFantino(fantino);
		partecipazione.setEdizione(edizione);
		this.partecipazioneRepository.save(partecipazione);
	}
	/*
	public void inizializza() {
	    int[][] partecipazioni = {
	        // Edizione 1 (luglio 2022)
	        {1, 8, 13, 1}, {2, 1, 11, 1}, {3, 4, 2, 1}, {4, 3, 14, 1}, {5, 2, 17, 1}, {6, 5, 3, 1},
	        {7, 6, 7, 1}, {8, 9, 5, 1}, {9, 10, 15, 1},{10,7,9,1}
	    };

	    for (int[] p : partecipazioni) {
	        Contrada c = contradaRepository.findById((long) p[2]).orElse(null);
	        Fantino f = fantinoRepository.findById((long) p[1]).orElse(null);
	        Cavallo cav = cavalloRepository.findById((long) p[0]).orElse(null);
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
	}*/

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
public Iterable<Cavallo> getCavalliBEdizioneId(Long id){
	return this.partecipazioneRepository.findAllCavalli(id);
}

public Iterable<Fantino> getFantiniByEdizioneId(Long id){
	return this.partecipazioneRepository.findAllFantini(id);
}


public Iterable<Contrada> getContradeByEdizioneId(Long id){
	return this.partecipazioneRepository.findAllContrade(id);
}


}
