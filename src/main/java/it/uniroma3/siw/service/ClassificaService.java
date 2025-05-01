package it.uniroma3.siw.service;
import it.uniroma3.siw.repository.EdizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.repository.ClassificaRepository;

@Service
public class ClassificaService {

  

    private final EdizioneRepository edizioneRepository;
@Autowired
private ClassificaRepository classificaRepository;

    ClassificaService(EdizioneRepository edizioneRepository) {
        this.edizioneRepository = edizioneRepository;
        
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


}
