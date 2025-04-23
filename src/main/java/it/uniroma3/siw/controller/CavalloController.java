package it.uniroma3.siw.controller;

import java.io.CharArrayReader;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Cavallo;

import it.uniroma3.siw.service.CavalloService;
import it.uniroma3.siw.service.ContradaService;

@Controller
public class CavalloController {
	@Autowired
private CavalloService cavalloService;
	
	@GetMapping("/")
	public String home(Model model) {
	    
	   Iterable<Cavallo> cavalli= this.cavalloService.getAll();
	   this.cavalloService.saveall(cavalli);
	   
	   for(Cavallo c:cavalli) {
		   model.addAttribute("cavallo", c);
		   
	   }
	   cavalloService.inizializza();
	   // model.addAttribute("cavallo", cavalli);
	    return "trovacavallo.html";
	}
	
	@PostMapping("/movie")
	public String newMovie(@ModelAttribute("cavallo") Cavallo cavallo, Model model) {
		this.cavalloService.save(cavallo);
		model.addAttribute("Cavallo", cavallo);
		return "movie.html";
	}
	
	
	 @GetMapping("/trova")
	    public String mostraForm() {
	        return "trovacavallo.html";
	    }
	 
	 
	  @GetMapping("/cavalli/{id}")
	    public String cercaPerId(@RequestParam Long id, Model model) {
	        Cavallo cavallo = this.cavalloService.getCavalloById(id);
	        model.addAttribute("cavallo", cavallo);
	        return "cavallo.html";
	    }
	  
	  
	  @GetMapping("/cavalli/cercatutti")
	    public String cercatutti( Model model) {
		    Iterable<Cavallo> cavalli=this.cavalloService.getAll();
	        model.addAttribute("cavalli", cavalli);
	        return "cavalli.html";
	    }
	  

	  
	  
	
	
	
}
