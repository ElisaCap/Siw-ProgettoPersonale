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
	
	
	@GetMapping("/insCavallo")
	public String insCavallo1(Model model) {
		model.addAttribute("cavallo", new  Cavallo());
		return "cavallo/insCavallo.html";
	}
	

	@PostMapping("/insCavallo")
	public String salvaCavallo(@ModelAttribute("cavallo") Cavallo cavallo, Model model) {
	    model.addAttribute(cavallo);
	    this.cavalloService.save(cavallo);
	    return "cavallo/cavallo.html";
	}

	
	
	 @GetMapping("/trovaCavallo")
	    public String mostraForm() {
	        return "cavallo/trovacavallo.html";
	    }
	 
	 
	  @GetMapping("/cavalli/{id}")
	    public String cercaPerId(@RequestParam Long id, Model model) {
	        Cavallo cavallo = this.cavalloService.getCavalloById(id);
	        model.addAttribute("cavallo", cavallo);
	        return "cavallo/cavallo.html";
	    }
	  
	  
	  @GetMapping("/cavalli/cercatutti")
	    public String cercatutti( Model model) {
		    Iterable<Cavallo> cavalli=this.cavalloService.getAll();
	        model.addAttribute("cavalli", cavalli);
	        return "cavallo/cavalli.html";
	    }
	  

	  
	  
	
	
	
}
