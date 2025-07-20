package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.User;
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
public Iterable<Commento> getByUser(User user) {
return this.commentoRepository.findAllByUser(user);
}
public List<Commento> getByUserAndEdizione(User user, Edizione edizione) {
    return commentoRepository.findByUserAndEdizione(user, edizione);
}
public Commento getById(Long id) {
	// TODO Auto-generated method stub
	return commentoRepository.findById(id).orElse(null);
}
public void deleteById(Long id) {
this.commentoRepository.deleteById(id);	
}


}
