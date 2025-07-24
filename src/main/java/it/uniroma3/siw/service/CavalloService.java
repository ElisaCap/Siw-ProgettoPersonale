package it.uniroma3.siw.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.CavalloRepository;
import jakarta.transaction.Transactional;

@Service
public class CavalloService {

    @Autowired
    private CavalloRepository cavalloRepository;
@Transactional
    public Cavallo getCavalloById(Long id) {
        return this.cavalloRepository.findById(id).orElse(null);
    }
@Transactional
    public Iterable<Cavallo> getAll() {
        return this.cavalloRepository.findAll();
    }

    public void save(Cavallo cavallo) {
        this.cavalloRepository.save(cavallo);
    }

    public void saveAll(Iterable<Cavallo> cavalli) {
        cavalli.forEach(this.cavalloRepository::save);
    }

    // ✅ Nuovo metodo: carica immagine da path e salva Cavallo
    
    public void nuovoCavallo(String nome, String razza, String imagePath) {
        Cavallo cavallo = new Cavallo();
        cavallo.setNome(nome);
        cavallo.setRazza(razza);
        try {
            ClassPathResource imgFile = new ClassPathResource("static" + imagePath);
            try (InputStream in = imgFile.getInputStream()) {
                cavallo.setImmagine(in.readAllBytes());
            }
        } catch (IOException e) {
            // puoi loggare o gestire diversamente
            cavallo.setImmagine(null); // o un'immagine di default
            System.err.println("Errore nel caricamento immagine per " + nome + ": " + e.getMessage());
        }
        this.cavalloRepository.save(cavallo);
    }
/*
    public void inizializza() {
        nuovoCavallo("Arestetulesu", "Mezzosangue", "/immaginisiw/cavalli/Arestetulesu.jpg");
        nuovoCavallo("Diodoro", "Mezzosangue", "/immaginisiw/cavalli/Diodoro.jpg");
        nuovoCavallo("Viso d’Angelo", "Mezzosangue", "/immaginisiw/cavalli/Viso d’Angelo.jpg");
        nuovoCavallo("Zenis", "Mezzosangue", "/immaginisiw/cavalli/Zenis.jpg");
        nuovoCavallo("Comancio", "Mezzosangue", "/immaginisiw/cavalli/Comancio.jpg");
        nuovoCavallo("Tale e Quale", "Mezzosangue", "/immaginisiw/cavalli/Tale e Quale.jpg");
        nuovoCavallo("Dorotea Dimmonia", "Mezzosangue", "/immaginisiw/cavalli/Dorotea Dimmonia.jpg");
        nuovoCavallo("Diosu de Campeda", "Mezzosangue", "/immaginisiw/cavalli/Diosu de Campeda.jpg");
        nuovoCavallo("Zio Frac", "Mezzosangue", "/immaginisiw/cavalli/Zio Frac.jpg");
        nuovoCavallo("Ares Elce", "Mezzosangue", "/immaginisiw/cavalli/Ares Elce.jpg");

    }*/
    
    
    
    public void deleteById(Long id) {
        this.cavalloRepository.deleteById(id);
    }
	public Iterable<Cavallo> getByNome(String nome) {
		// TODO Auto-generated method stub
		return cavalloRepository.findAllByNome(nome);
	}
	
 
    
    
    
}
