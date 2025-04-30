package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.service.ContradaService;

@Controller
public class ContradaController {
@Autowired
private ContradaService contradaService;
private boolean inserite=false;
@GetMapping("/contrade")
public String mostraTutte(Model model) {
	if(this.inserite==false){
	this.contradaService.inizializza();
	
	
	}
	model.addAttribute("contrade",this.contradaService.getAll());
	this.inserite=true;
	return "contrade.html";
}

@GetMapping("/contrade/{id}")
public String mostraContrada(@RequestParam Long id, Model model) {
	//Long id2= id.;
	Contrada contrada=this.contradaService.getByid(id);
	model.addAttribute("contrada",contrada);
			return "contrada.html";
}



}
