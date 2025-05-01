package it.uniroma3.siw.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.repository.FantinoRepository;

@Service
public class FantinoService {
	@Autowired
private FantinoRepository fantinoRepository;

public void save(Fantino fantino) {
	this.fantinoRepository.save(fantino);
}

public void aggiungiFantino(String nome,String cognome,String urlImmagine,LocalDate dataNascita) {
	Fantino fantino=new Fantino();
	fantino.setNome(nome);
	fantino.setCognome(cognome);
	fantino.setUrlImmagine(urlImmagine);
	fantino.setDataNascita(dataNascita);
	this.fantinoRepository.save(fantino);
}



	public void inizializza() {
	    aggiungiFantino("Giovanni", "Atzeni", "url_img_atzeni", LocalDate.of(1985, 9, 15)); // Tittia
	    aggiungiFantino("Carlo", "Sanna", "url_img_sanna", LocalDate.of(1989, 4, 4)); // Brigante
	    aggiungiFantino("Federico", "Arri", "url_img_arri", LocalDate.of(1994, 8, 4)); // Ares
	    aggiungiFantino("Gabriele", "Piras", "url_img_piras", LocalDate.of(1997, 12, 10)); // Tempesta
	    aggiungiFantino("Sebastiano", "Murtas", "url_img_murtas", LocalDate.of(1995, 2, 8)); // Grandine
	    aggiungiFantino("Andrea", "Coghe", "url_img_coghe", LocalDate.of(1993, 6, 22)); // Tempesta (Padre)
	    aggiungiFantino("Silvano", "Mulas", "url_img_mulas", LocalDate.of(1976, 1, 7)); // Voglia
	    aggiungiFantino("Jonathan", "Bartoletti", "url_img_bartoletti", LocalDate.of(1981, 9, 16)); // Scompiglio
	    aggiungiFantino("Antonio", "Siri", "url_img_siri", LocalDate.of(1985, 12, 18)); // Amsicora
	    aggiungiFantino("Francesco", "Caria", "url_img_caria", LocalDate.of(1987, 3, 5)); // Tremendo
	    aggiungiFantino("Dario", "Colombati", "url_img_colombati", LocalDate.of(1991, 10, 11)); // Pirichittu
	    aggiungiFantino("Adriano", "Cozzoli", "url_img_cozzoli", LocalDate.of(1996, 11, 14)); // Nappa II
	    aggiungiFantino("Valter", "Pusceddu", "url_img_pusceddu", LocalDate.of(1974, 8, 1)); // Bighino
	    aggiungiFantino("Gavino", "Sanna", "url_img_gavino", LocalDate.of(1999, 9, 12)); // Nulese
	

}


	public Fantino getByid(Long id) {
		return this.fantinoRepository.findById(id).get();
	}
	public Iterable<Fantino>getAll(){
		return this.fantinoRepository.findAll();
	}
	
}
