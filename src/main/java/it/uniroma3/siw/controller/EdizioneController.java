package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.service.ClassificaService;
import it.uniroma3.siw.service.EdizioneService;

@Controller
public class EdizioneController {
	@Autowired
private EdizioneService edizioneSerivice;
	@Autowired
	private EdizioneRepository edizioneRepository;
	@Autowired
	private ClassificaService classificaService;
	@Autowired
	private ElementoClassificaRepository elementoClassificaRepository;
	
	
@GetMapping("/edizioni")
public String edizioni(Model model) {
	model.addAttribute("edizioni",this.edizioneSerivice.getAll());
	return "edizione/edizioni.html";
}
@GetMapping("/edizione/{id}")
public String edizione(@RequestParam Long id, Model model) {
	model.addAttribute("edizione",this.edizioneSerivice.getById(id));
	return "edizione/edizione.html";
}
@GetMapping("/insEdizione")
public String insEdizione(Model model) {
	Edizione edizione=new Edizione();
	model.addAttribute("edizione",edizione);
	return "edizione/nuovaEdizione.html";
}
@PostMapping("/insEdizione")
public String saveEdizione(@ModelAttribute("edizione") Edizione edizione) {
    // Crea la classifica associata
    Classifica classifica = new Classifica();
    classifica.setEdizione(edizione); // Associazione bidirezionale
    edizione.setClassifica(classifica);

    // Salva tutto
    edizioneSerivice.save(edizione); // CascadeType.ALL farà salvare anche la classifica

    return "redirect:/edizione/%7Bid%7D?id=" + edizione.getId();
}


@GetMapping("/getClassifica/{id}")
public String classificaEdId(@RequestParam Long id,Model model) {
	Classifica classifica=this.classificaService.getById(id);
	model.addAttribute("classifica",classifica);
	model.addAttribute("elementiClassifica",this.elementoClassificaRepository.findAllByClassifica(classifica));
	 if (id == null) {
         return "errore"; // Gestisci un eventuale errore
     }
	return "redirect:/classifica/"+ id;
	
}

@GetMapping("/edizioniCavallo/{id}")
public String edizioneCavallo(@PathVariable Long id, Model model) {
    Iterable<Edizione> edizioni = edizioneRepository.findEdizioniByCavalloId(id);
    model.addAttribute("edizioni", edizioni);
    return "edizione/edizioni";
}






}
