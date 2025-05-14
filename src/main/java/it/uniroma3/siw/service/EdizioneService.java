package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.repository.ClassificaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;

@Service
public class EdizioneService {

    private final ClassificaRepository classificaRepository;

  
	@Autowired
private EdizioneRepository edizioneRepository;
	private Long id;
	private LocalDate data;
	private String urlImmagine;

    EdizioneService( ClassificaRepository classificaRepository) {
       
        this.classificaRepository = classificaRepository;
    }
	private void aggiungiEdizione(LocalDate data,String urlImmagine) {
		Edizione edizione=new Edizione();
		edizione.setData(data);
		edizione.setUrlImmagine(urlImmagine);
		this.edizioneRepository.save(edizione);
	}
	public void aggiungiClassifica() {
		for(Edizione ed:this.edizioneRepository.findAll()) {
			ed.setClassifica(classificaRepository.findById(ed.getId()).get());
		}
	}
	
public void inizializza() {
	this.aggiungiEdizione(LocalDate.of(2022, 7, 2), urlImmagine);
	this.aggiungiEdizione(LocalDate.of(2022, 8, 17), urlImmagine);
	this.aggiungiEdizione(LocalDate.of(2023, 7, 2), urlImmagine);
}
	
public Iterable<Edizione>getAll(){
	return this.edizioneRepository.findAll();
}
public Edizione getById(Long id) {
	return this.edizioneRepository.findById(id).get();
}

public Iterable<Edizione>getAllById(Long id){
 List<Edizione> edizioni=new ArrayList<>();
 for(Edizione edizione:this.edizioneRepository.findAll())
 {
	 if(edizione.getId()==id)
		 edizioni.add(edizione);
 }
	return edizioni;
}













}
