package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Cavallo;

import it.uniroma3.siw.service.CavalloService;

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
	 
	 
	  @PostMapping("/cavalli/cerca")
	    public String cercaPerId(@ModelAttribute("cavallo") @RequestParam Long id, Model model) {
	        Cavallo cavallo = this.cavalloService.getCavalloById(id);
	        model.addAttribute("cavallo", cavallo);
	        return "cavallo.html";
	    }
	  @PostMapping("/cavalli/cercatutti")
	    public String cercatutti(Model model) {
	        model.addAttribute("cavallo", this.cavalloService.getAll());
	        return "cavalli.html";
	    }
	  
	  @GetMapping("/cavalli/cercatutti")
	  public String getMethodName(Model model) {
		  model.addAttribute("cavallo", this.cavalloService.getAll());
	  	return "cavalli.html";
	  }
	  
	  
	
	
	
}
