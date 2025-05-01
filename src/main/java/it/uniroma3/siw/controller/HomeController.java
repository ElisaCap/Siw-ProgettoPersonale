package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.service.*;


@Controller
public class HomeController {

    private final ElementoClassificaRepository elementoClassificaRepository;

    private final ContradaService contradaService;
	@Autowired
	private CavalloService cavalloService;
	@Autowired
	private FantinoService fantinoService;
	@Autowired
	private EdizioneService edizioneService;
	@Autowired
	private PartecipazioneService partecipazioneService;
	@Autowired
	private ClassificaService classificaService;
	@Autowired
	private ElementoClassificaService elementoClassificaService;
	private boolean generati=false;

    HomeController(ContradaService contradaService, ElementoClassificaRepository elementoClassificaRepository) {
        this.contradaService = contradaService;
        this.elementoClassificaRepository = elementoClassificaRepository;
    } 
	@GetMapping("/")
	public String home(Model model) {
		 if(this.generati==false) {
			 this.contradaService.inizializza();
		this.fantinoService.inizializza();
		   cavalloService.inizializza(); 
		   this.edizioneService.inizializza();
		   this.classificaService.inizializza();
		   this.edizioneService.aggiungiClassifica();
		   this.partecipazioneService.inizializza();
		   this.elementoClassificaService.inizializza();
this.generati=true;
	
	  
	 	 
	    }
	   
	  
	    return "trovacavallo.html";
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
