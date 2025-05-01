package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.controller.CavalloController;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.CavalloRepository;

@Service

public class CavalloService {

    
	@Autowired
	private CavalloRepository cavalloRepository;

  
	public Cavallo getCavalloById(Long id) {
		
		return this.cavalloRepository.findById(id).orElse(null);
	}
	public Iterable<Cavallo>getAll(){
		return this.cavalloRepository.findAll();
	}
	
public void save(Cavallo cavallo) {
	this.cavalloRepository.save(cavallo);
}
public void saveall(Iterable<Cavallo> cavalli) {
	
	for(Cavallo c:cavalli) {
		
	
	this.cavalloRepository.save(c);
}}

public void nuovoCavallo(String nome,String Razza,String Url) {
	Cavallo cavallo=new Cavallo();
	cavallo.setNome(nome);
	cavallo.setRazza(Razza);
		cavallo.setUrlImmagine(Url);
		this.cavalloRepository.save(cavallo);
}

public void inizializza() {
	
	
	    // Cavalli unici dei Palii 2 luglio 2022, 17 agosto 2022, 2 luglio 2023

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


}
