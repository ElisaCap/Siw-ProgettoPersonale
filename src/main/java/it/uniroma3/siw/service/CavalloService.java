package it.uniroma3.siw.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.InputStream;
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

    public void inizializza() {
        nuovoCavallo("Una Per Tutti", "Mezzosangue", "/immaginiCavalli/una_per_tutti.jpg");
        nuovoCavallo("Vitzichesu", "Mezzosangue", "/immaginiCavalli/vitzichesu.jpg");
        nuovoCavallo("Volpino", "Mezzosangue", "/immaginiCavalli/volpino.jpg");
        nuovoCavallo("Schietta", "Mezzosangue", "/immaginiCavalli/schietta.jpg");
        nuovoCavallo("Vankook", "Mezzosangue", "/immaginiCavalli/vankook.jpg");
        nuovoCavallo("Zentile", "Mezzosangue", "/immaginiCavalli/zentile.jpg");
        nuovoCavallo("Uragano Rosso", "Mezzosangue", "/immaginiCavalli/uragano_rosso.jpg");
        nuovoCavallo("Viso d’Angelo", "Mezzosangue", "/immaginiCavalli/viso_d_angelo.jpg");
        nuovoCavallo("Zio Frac", "Mezzosangue", "/immaginiCavalli/zio_frac.jpg");
        nuovoCavallo("Reo Confesso", "Mezzosangue", "/immaginiCavalli/reo_confesso.jpg");
        nuovoCavallo("Remorex", "Mezzosangue", "/immaginiCavalli/remorex.jpg");
        nuovoCavallo("Astoriux", "Mezzosangue", "/immaginiCavalli/astoriux.jpg");
        nuovoCavallo("Arestetulesu", "Mezzosangue", "/immaginiCavalli/arestetulesu.jpg");
        nuovoCavallo("Ungaros", "Mezzosangue", "/immaginiCavalli/ungaros.jpg");
        nuovoCavallo("Violenta da Clodia", "Mezzosangue", "/immaginiCavalli/violenta_da_clodia.jpg");
        nuovoCavallo("Solo Tue Due", "Mezzosangue", "/immaginiCavalli/solo_tue_due.jpg");
        nuovoCavallo("Tabacco", "Mezzosangue", "/immaginiCavalli/tabacco.jpg");
        nuovoCavallo("Veranu", "Mezzosangue", "/immaginiCavalli/veranu.jpg");
        nuovoCavallo("Abbasantesa", "Mezzosangue", "/immaginiCavalli/abbasantesa.jpg");
        nuovoCavallo("Anda e Bola", "Mezzosangue", "/immaginiCavalli/anda_e_bola.jpg");
    }
    
    
    
    public void deleteById(Long id) {
        this.cavalloRepository.deleteById(id);
    }
 
    
    
    
}
