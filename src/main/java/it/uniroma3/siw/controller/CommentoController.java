package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.service.CommentoService;
import it.uniroma3.siw.service.EdizioneService;

@Controller
public class CommentoController {

	@Autowired
    private CommentoService commentoService;
	@Autowired
	   private EdizioneService edizioneService;
	@GetMapping("/insCommento/{id}")
    public String insCommnento(@PathVariable Long id,Model model) {
    	Edizione edizione=edizioneService.getById(id);
        model.addAttribute("edizione",edizione);
        Commento commento=new Commento();
        commento.setEdizione(edizione);
        model.addAttribute("commento", commento);
        model.addAttribute("id",id);
        return "commento/insCommento.html";
    }

    @PostMapping("/salvaCommento")
    public String salvaCommento( @ModelAttribute("commento") Commento commento, Model model) {
    	this.commentoService.save(commento);
        return "redirect:/commentiEdizione/"+commento.getEdizione().getId();
    }
    
    @GetMapping("/commentiEdizione/{id}")
    public String mostraCommenti(@PathVariable Long id, Model model) {
    	Edizione edizione=edizioneService.getById(id);
        model.addAttribute("commenti",commentoService.getByEdizione(edizione) );
        model.addAttribute("id",id);
        return "commento/commenti.html";
    }
	
	
	
}
