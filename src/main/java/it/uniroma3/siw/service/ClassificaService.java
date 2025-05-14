package it.uniroma3.siw.service;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.repository.ClassificaRepository;

@Service
public class ClassificaService {

    private final ElementoClassificaService elementoClassificaService;

    private final ElementoClassificaRepository elementoClassificaRepository;

  

    private final EdizioneRepository edizioneRepository;
@Autowired
private ClassificaRepository classificaRepository;

    ClassificaService(EdizioneRepository edizioneRepository, ElementoClassificaRepository elementoClassificaRepository, ElementoClassificaService elementoClassificaService) {
        this.edizioneRepository = edizioneRepository;
        this.elementoClassificaRepository = elementoClassificaRepository;
        this.elementoClassificaService = elementoClassificaService;
        
    }
	
public void inserisciClassifica(Edizione ed)	{
	Classifica classifica=new Classifica();
	classifica.setEdizione(ed);
	this.classificaRepository.save(classifica);
}
	public void inizializza() {
		for(Edizione ed:edizioneRepository.findAll()) {
			this.inserisciClassifica(ed);
		}
	}

	
	public Classifica getById(Long id) {
		return this.classificaRepository.findById(id).get();
	}
	public void aggiungiElementi() {
		for(Classifica classifica : this.classificaRepository.findAll()) {
			List<ElementoClassifica>elementic=new ArrayList<>();
			for(ElementoClassifica elemento : elementoClassificaRepository.findAll()) {
				if (elemento.getClassifica().getEdizione().getId() == classifica.getEdizione().getId()) {
					elementic.add(elemento);
					classifica.setElementiClassifica(elementic);
				}
			}
			
		}
	}

	

}





