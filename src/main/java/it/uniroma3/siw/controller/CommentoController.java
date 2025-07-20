package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;

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
        Edizione edizione = edizioneService.getById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Commento> tuttiCommenti = (List)commentoService.getByEdizione(edizione);
        List<Commento> commentiUtente = null;
        User userLoggato = null;

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            String username = auth.getName();
            userLoggato = userService.getUserByUsername(username);
            commentiUtente = (List)commentoService.getByUserAndEdizione(userLoggato, edizione); // <-- crea questo metodo
            model.addAttribute("userLoggato", userLoggato);
        }

        // Rimuovi i commenti dell'utente da quelli generali per evitare duplicati
        if (commentiUtente != null) {
            tuttiCommenti.removeAll(commentiUtente);
            model.addAttribute("tuoiCommenti", commentiUtente);
        }

        model.addAttribute("commenti", tuttiCommenti);
        model.addAttribute("id", id);

        return "commento/commenti.html";
    }
    
    @PostMapping("/eliminaCommento/{id}")
    public String eliminaCommento(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return "redirect:/login"; // Non autenticato
        }

        Commento commento = commentoService.getById(id);

        if (commento == null) {
            return "redirect:/"; // Commento non trovato
        }

        User userLoggato = userService.getUserByUsername(auth.getName());

        // Sicurezza: controllo che il commento sia dell'utente loggato
        if (!commento.getUser().getId().equals(userLoggato.getId())) {
            return "redirect:/accesso-negato"; // o pagina di errore personalizzata
        }

        Edizione edizione = commento.getEdizione();
        commentoService.deleteById(id);

        return "redirect:/commentiEdizione/" + edizione.getId();
    }

	
}
