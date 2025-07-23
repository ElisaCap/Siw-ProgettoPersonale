package it.uniroma3.siw.service;

import java.util.List;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CommentoRepository;

@Service
public class CommentoService {

    private final UserRepository userRepository;

    private final CredentialsRepository credentialsRepository;

    private final EdizioneRepository edizioneRepository;
	@Autowired
	private CommentoRepository commentoRepository;

    CommentoService(EdizioneRepository edizioneRepository, CredentialsRepository credentialsRepository, UserRepository userRepository) {
        this.edizioneRepository = edizioneRepository;
        this.credentialsRepository = credentialsRepository;
        this.userRepository = userRepository;
    }
	@Transactional
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


@Transactional
public void inserisciCommento(Long userId, Long edizioneId, String utente, String contenuto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User non trovato con ID: " + userId));
    Edizione edizione = edizioneRepository.findById(edizioneId)
        .orElseThrow(() -> new IllegalArgumentException("Edizione non trovata con ID: " + edizioneId));

    // opzionale, per sicurezza
    user = userRepository.save(user);
    edizione = edizioneRepository.save(edizione);

    Commento commento = new Commento();
    commento.setUser(user);
    commento.setEdizione(edizione);
    commento.setUtente(utente);
    commento.setContenuto(contenuto);

    commentoRepository.save(commento);
}






}
