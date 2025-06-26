package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.repository.CommentoRepository;

@Service
public class CommentoService {
	@Autowired
	private CommentoRepository commentoRepository;
	
public void save(Commento commento) {
	this.commentoRepository.save(commento);
}
public Iterable<Commento>getByEdizione(Edizione edizione){
	return this.commentoRepository.findByEdizione(edizione);
}

}
