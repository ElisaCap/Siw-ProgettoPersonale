package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.model.User;

@Controller
public class CommentoController {
	@Autowired
	private UserService userService;

	@Autowired
    private CommentoService commentoService;
	@Autowired
	   private EdizioneService edizioneService;
	@GetMapping("/insCommento/{id}/{idUser}")
    public String insCommnento(@PathVariable Long id, @PathVariable Long idUser,Model model) {
	    User user = userService.findById(idUser);
		Edizione edizione=edizioneService.getById(id);
        model.addAttribute("edizione",edizione);
        Commento commento=new Commento();
        commento.setEdizione(edizione);
        commento.setUser(user);
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
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  	    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
  	        String username = auth.getName();
  	        User user = userService.getUserByUsername(username);
  	        model.addAttribute("userLoggato", user);
  	    } else {
  	        model.addAttribute("userLoggato", null);
  	    }
        model.addAttribute("commenti",commentoService.getByEdizione(edizione) );
        model.addAttribute("id",id);
        return "commento/commenti.html";
    }
	
	
	
}
