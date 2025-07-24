package it.uniroma3.siw.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.repository.FantinoRepository;
import jakarta.transaction.Transactional;

@Service
public class FantinoService {
	@Autowired
private FantinoRepository fantinoRepository;

public void save(Fantino fantino) {
	this.fantinoRepository.save(fantino);
}


public void deleteById(Long id) {
    this.fantinoRepository.deleteById(id);
}





public void aggiungiFantino(String nome,String cognome,String urlImmagine,LocalDate dataNascita) {
	Fantino fantino=new Fantino();
	fantino.setNome(nome);
	fantino.setCognome(cognome);
	fantino.setDataNascita(dataNascita);
	
	try {
        ClassPathResource imgFile = new ClassPathResource("static" + urlImmagine);
        try (InputStream in = imgFile.getInputStream()) {
            fantino.setUrlImmagine(in.readAllBytes());
        }
    } catch (IOException e) {
        // puoi loggare o gestire diversamente
        fantino.setUrlImmagine(null); // o un'immagine di default
        System.err.println("Errore nel caricamento immagine per " + nome + ": " + e.getMessage());
    }
	
	this.fantinoRepository.save(fantino);
}


/*
	public void inizializza() {
		aggiungiFantino("Giovanni", "Atzeni", "/immaginisiw/fantini/Giovanni Atzeni.webp", LocalDate.of(1986, 4, 22)); // Tittia
		aggiungiFantino("Giuseppe", "Zedde", "/immaginisiw/fantini/gius zedde.webp", LocalDate.of(1992, 10, 13)); // Gingillo
		aggiungiFantino("Andrea", "Sanna", "/immaginisiw/fantini/and sanna.webp", LocalDate.of(1997, 12, 2)); // Virgola
		aggiungiFantino("Jonatan", "Bartoletti", "/immaginisiw/fantini/john bart.webp", LocalDate.of(1990, 11, 30)); // Scompiglio
		aggiungiFantino("Giosuè", "Carboni", "/immaginisiw/fantini/gios carb.webp", LocalDate.of(1980, 5, 6)); // Carburo
		aggiungiFantino("Enrico", "Bruschelli", "/immaginisiw/fantini/En brus.webp", LocalDate.of(1984, 5, 17)); // Bellocchio
		aggiungiFantino("Antonio", "Mula", "/immaginisiw/fantini/ant mula.webp", LocalDate.of(1988, 7, 17)); // Shardana
		aggiungiFantino("Francesco", "Caria", "/immaginisiw/fantini/fran caria.webp", LocalDate.of(1990, 1, 22)); // Tremendo
		aggiungiFantino("Michel", "Putzu", "/immaginisiw/fantini/mic putzu.webp", LocalDate.of(1990, 2, 17)); // Spago
		aggiungiFantino("Carlo", "Sanna", "/immaginisiw/fantini/car sanna.webp", LocalDate.of(1989, 4, 4)); // Brigante


}*/

@Transactional
	public Fantino getByid(Long id) {
		return this.fantinoRepository.findById(id).orElse(null);
	}
@Transactional
	public Iterable<Fantino>getAll(){
		return this.fantinoRepository.findAllByOrderByCognomeAsc();
	}


public Iterable<Fantino> getAllByCognome(String cognome) {
	// TODO Auto-generated method stub
	return fantinoRepository.findAllByCognome(cognome);
}
	
}
