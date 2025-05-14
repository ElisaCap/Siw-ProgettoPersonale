package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.service.FantinoService;

@Controller
public class FantinoController {
	@Autowired
private FantinoService fantinoService;
	

@GetMapping("/insFantino")
	public String insfantino(Model model) {
		model.addAttribute("fantino", new  Fantino());
		return "fantino/insFantino.html";
	}
	

	@PostMapping("/insFantino")
	public String salvafantino(@ModelAttribute("fantino") Fantino fantino, Model model) {
	    model.addAttribute(fantino);
	    this.fantinoService.save(fantino);
	    return "fantino/fantino.html";
	}

	
	
	 @GetMapping("/trovaFantino")
	    public String mostraFantino() {
	        return "fantino/trovaFantino.html";
	    }
	 
	 
	  @GetMapping("/fantini/{id}")
	    public String cercaPerId(@RequestParam Long id, Model model) {
	        Fantino fantino = this.fantinoService.getByid(id);
	        model.addAttribute("fantino", fantino);
	        return "fantino/fantino.html";
	    }
	  
	  
	  @GetMapping("/fantini/cercatutti")
	    public String cercatutti( Model model) {
		    Iterable<Fantino> fantini=this.fantinoService.getAll();
	        model.addAttribute("fantini", fantini);
	        return "fantino/fantini.html";
	    }
	
	
}
