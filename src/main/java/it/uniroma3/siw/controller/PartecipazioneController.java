package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.model.Partecipazione;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.service.CavalloService;
import it.uniroma3.siw.service.ContradaService;
import it.uniroma3.siw.service.EdizioneService;
import it.uniroma3.siw.service.FantinoService;
import it.uniroma3.siw.service.PartecipazioneService;


@Controller
public class PartecipazioneController {

    private final EdizioneRepository edizioneRepository;
	@Autowired
	private CavalloService cavalloService;
	@Autowired
	private FantinoService fantinoService;
	@Autowired
	private EdizioneService edizioneService;
	@Autowired
	private PartecipazioneService partecipazioneService;
	@Autowired
	private ContradaService contradaService;

    PartecipazioneController(EdizioneRepository edizioneRepository) {
        this.edizioneRepository = edizioneRepository;
    }
    @PreAuthorize("hasRole('ADMIN')")
 @GetMapping("/insPartecipazione")
 public String insPartecipazione(Model model) {
	 model.addAttribute("partecipazione",new Partecipazione());
	 model.addAttribute("cavalli",cavalloService.getAll());
	 model.addAttribute("edizioni",edizioneService.getAll());
	 model.addAttribute("fantini", fantinoService.getAll());
	 model.addAttribute("contrade", contradaService.getAll());

	 return "partecipazione/NuovaPartecipazione.html";
 }
 
 
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping("/insPartecipazione")
public String savePartecipazione(@ModelAttribute("partecipazione") Partecipazione partecipazione, Model model ) {
	this.partecipazioneService.save(partecipazione);
	return "redirect:/edizione/"+partecipazione.getEdizione().getId();
}
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/partecipazione/delete/{id}")
public String eliminaPartecipazione(@PathVariable Long id, Model model) {
   Partecipazione p=partecipazioneService.getById(id);
   Classifica classifica=p.getEdizione().getClassifica();
    partecipazioneService.deleteById(id);
    model.addAttribute("classifica", classifica);
    model.addAttribute("elementiClassifica", classifica.getElementiClassifica());
    model.addAttribute("partecipazioni", classifica.getEdizione().getPartecipazioni());
    return "redirect:/classifica/"+classifica.getEdizioneId(); // oppure redirect a cercatutti
}

    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/insPartecipazione/{id}")
public String insPartecipazione(@PathVariable Long id, Model model) {
    Edizione edizione = edizioneRepository.findById(id).get();

    Partecipazione partecipazione = new Partecipazione();
    partecipazione.setEdizione(edizione); // o setta direttamente l'oggetto Edizione, dipende dal modello

    model.addAttribute("partecipazione", partecipazione);
    model.addAttribute("cavalli", cavalloService.getAll());
    model.addAttribute("fantini", fantinoService.getAll());
    model.addAttribute("contrade", contradaService.getAll());

    return "partecipazione/NuovaPartecipazione.html";
}

@GetMapping("/partCavallo/{id}")
public String partCavallo(@PathVariable Long id,Model model) {
	Cavallo cavallo=cavalloService.getCavalloById(id);
	//List<Partecipazione> partecipazioni=(List)this.partecipazioneService.getByCavallo(cavallo);;
	List<Partecipazione> partecipazioni=(List)partecipazioneService.getByCavallo(cavallo);
	model.addAttribute("partecipazioni",partecipazioni);
	return "partecipazione/partecipazioni.html";
}

@GetMapping("/partFantino/{id}")
public String partFantino(@PathVariable Long id,Model model) {
	Fantino fantino=fantinoService.getByid(id);
	model.addAttribute("partecipazioni",this.partecipazioneService.getByFantino(fantino));
	return "partecipazione/partecipazioni.html";
}

@GetMapping("/partContrada/{id}")
public String partContrada(@PathVariable Long id,Model model) {
	Contrada contrada=contradaService.getByid(id);
	model.addAttribute("partecipazioni",this.partecipazioneService.getByContrada(contrada));
	return "partecipazione/partecipazioni.html";
}




	
}
