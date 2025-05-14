package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.service.EdizioneService;

@Controller
public class EdizioneController {
	@Autowired
private EdizioneService edizioneSerivice;
	
	
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


}
