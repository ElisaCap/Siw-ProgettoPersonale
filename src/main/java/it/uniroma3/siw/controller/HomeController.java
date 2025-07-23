package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.service.*;


@Controller
public class HomeController {
@Autowired
    private  ElementoClassificaRepository elementoClassificaRepository;
 @Autowired
    private  ContradaService contradaService;
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

	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/home")
	public String home(Model model) {
		 if(this.generati==false) {
			 this.contradaService.inizializza();
		this.fantinoService.inizializza();
		   cavalloService.inizializza(); 
		   this.edizioneService.inizializza();
		   this.classificaService.inizializza();
		   //this.edizioneService.aggiungiClassifica();
		  this.partecipazioneService.inizializza();
		  this.elementoClassificaService.inizializza();
		   //this.classificaService.aggiungiElementi();
this.generati=true;
	
	  
	 	 
	    
	   
	  
	    
	}
		 return "homepage.html";	 
	
	
	}
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/visualizza")
	public String trova(Model model) {
		return "cavallo/trovacavallo.html";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/inserisci")
	public String inserisci(Model model) {
		return "inserisci.html";
	}
	
    @GetMapping("/403")
    public String accessDenied() {
        return "403.html";
    }
    
}
	
	
	
	
	
	
	
	
	
	
	

