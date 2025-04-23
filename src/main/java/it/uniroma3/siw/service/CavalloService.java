package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.controller.CavalloController;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.CavalloRepository;

@Service

public class CavalloService {

    
	@Autowired
	private CavalloRepository cavalloRepository;

  
	public Cavallo getCavalloById(Long id) {
		
		return this.cavalloRepository.findById(id).orElse(null);
	}
	public Iterable<Cavallo>getAll(){
		return this.cavalloRepository.findAll();
	}
	
public void save(Cavallo cavallo) {
	this.cavalloRepository.save(cavallo);
}
public void saveall(Iterable<Cavallo> cavalli) {
	for(Cavallo c:cavalli) {
		
	
	this.cavalloRepository.save(c);
}}

public void nuovoCavallo(String nome,String Razza,String Url) {
	Cavallo cavallo=new Cavallo();
	cavallo.setNome(nome);
	cavallo.setRazza(Razza);
		cavallo.setUrlImmagine(Url);
		this.cavalloRepository.save(cavallo);
}

public void inizializza() {
	
	this.nuovoCavallo("Tabavvp", "Purosangue", "/tabacco.jpg");
	this.nuovoCavallo("Tabavvp2", "Purosangue2", "/tabacco.jpg");
}


}
