package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.repository.ClassificaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import jakarta.transaction.Transactional;

@Service
public class EdizioneService {

    private final ElementoClassificaRepository elementoClassificaRepository;

    private final ClassificaRepository classificaRepository;

  
	@Autowired
private EdizioneRepository edizioneRepository;
	private Long id;
	private LocalDate data;
	private String urlImmagine;

    EdizioneService( ClassificaRepository classificaRepository, ElementoClassificaRepository elementoClassificaRepository) {
       
        this.classificaRepository = classificaRepository;
       
        this.elementoClassificaRepository = elementoClassificaRepository;
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

public void save(Edizione edizione) {
	this.edizioneRepository.save(edizione);
}



@Transactional
public void deleteById(Long id) {
    Optional<Classifica> optionalClassifica = classificaRepository.findById(id);
    if (optionalClassifica.isPresent()) {
        Classifica classifica = optionalClassifica.get();

        // Rimuovi ogni elementoClassifica
        for (ElementoClassifica ec : new ArrayList<>(classifica.getElementiClassifica())) {
            ec.setClassifica(null); // scollega il riferimento
            elementoClassificaRepository.delete(ec);
        }

        classifica.getElementiClassifica().clear();
        classificaRepository.save(classifica); // aggiorna l'entità nel DB

        classificaRepository.delete(classifica); // ora si può eliminare
    }

    edizioneRepository.deleteById(id);
}






}
