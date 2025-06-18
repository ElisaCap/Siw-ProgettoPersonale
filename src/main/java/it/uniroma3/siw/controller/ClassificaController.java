package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.repository.ClassificaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.service.ClassificaService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

@Controller
public class ClassificaController {
	
@Autowired
    private  EdizioneRepository edizioneRepository;
private Classifica classificaCorrente;
private int numContrade;
@Autowired
    private  ElementoClassificaRepository elementoClassificaRepository;
@Autowired
private ClassificaService classificaService;
@Autowired
private PartecipazioneRepository partecipazioneRepository;
@Autowired
private ClassificaRepository classificaRepository;

    ClassificaController(ElementoClassificaRepository elementoClassificaRepository, EdizioneRepository edizioneRepository) {
        this.elementoClassificaRepository = elementoClassificaRepository;
        this.edizioneRepository = edizioneRepository;
    }
@GetMapping("/classifica/{id}")
public String classificaId(@PathVariable("id") Long id,Model model) {
	Classifica classifica=this.classificaService.getById(id);
	model.addAttribute("classifica",classifica);
	model.addAttribute("elementiClassifica",this.elementoClassificaRepository.findAllByClassifica(classifica));
	 if (id == null) {
         return "errore"; // Gestisci un eventuale errore
     }
	return "classifica/classifica.html";
	
}



@GetMapping("/insEdizioneClassifica")
public String insEdizione(Model model) {
	//Classifica classifica=new Classifica();
	//model.addAttribute("classifica", classifica);
	  model.addAttribute("edizioni", edizioneRepository.findAll());
      return "classifica/insEdizione.html";
}

@PostMapping("/saveEdizioneClassifica")
public String saveEdizioneClassifica(@RequestParam("edizioneId") Long edizioneId, Model model) {
    Optional<Edizione> edizioneOpt = edizioneRepository.findById(edizioneId);
    
    if (edizioneOpt.isEmpty()) {
        // se qualcuno "barato" prova a inviare un ID inesistente
        return "redirect:/insEdizioneClassifica?errore=edizione_non_valida";
    }

    Edizione edizione = edizioneOpt.get();

    // cerca la classifica legata a questa edizione, oppure la crea
    Classifica classifica = classificaRepository.findByEdizione(edizione)
                                .orElseGet(() -> {
                                    Classifica c = new Classifica();
                                    c.setEdizione(edizione);
                                    return classificaRepository.save(c);
                                });

    model.addAttribute("classifica", classifica);
    model.addAttribute("edizione", edizione);
    model.addAttribute("edizioneId", edizione.getId());

    return "classifica/insNumContrade.html";
}


/*
@GetMapping("/insNumContrade")
public String insNumContrade(@RequestParam("edizioneId") Long edizioneId,Model model) {
	model.addAttribute("classifica",classificaCorrente);
	return "classifica/insNumContrade.html";
}*/

@PostMapping("/saveNumContrade")
public String setNumContrade(@ModelAttribute("classifica") Classifica classifica,
                             @RequestParam("num") int num,
                             Model model) {

    List<ElementoClassifica> elementi = new ArrayList<>();
    for (int i = 0; i < num; i++) {
        ElementoClassifica el = new ElementoClassifica();
        el.setPosizione(i + 1);
        elementi.add(el);
    }

    classifica.setElementiClassifica(elementi);

    model.addAttribute("classifica", classifica);
    model.addAttribute("partecipazioni", partecipazioneRepository.findAll());
    model.addAttribute("num", num);
    return "classifica/insClassifica.html";
}
@PostMapping("/insClassifica")
public String insClassifica(@RequestParam("edizioneId") Long edizioneId,
                            @RequestParam("numContrade") int numContrade,
                            Model model) {

    Edizione edizione = edizioneRepository.findById(edizioneId).orElse(null);
    if (edizione == null) return "errore";

    // Cerca classifica esistente o crea nuova
    Classifica classifica = classificaRepository.findByEdizione(edizione)
        .orElseGet(() -> {
            Classifica nuova = new Classifica();
            nuova.setEdizione(edizione);
            return classificaRepository.save(nuova); // salva subito!
        });

    // Solo se non già presenti
    if (classifica.getElementiClassifica() == null || classifica.getElementiClassifica().isEmpty()) {
        List<ElementoClassifica> elementi = new ArrayList<>();
        for (int i = 0; i < numContrade; i++) {
            ElementoClassifica el = new ElementoClassifica();
            el.setPosizione(i + 1);
            el.setClassifica(classifica); // relaziona
            elementi.add(el);
        }
        classifica.setElementiClassifica(elementi);
        classificaRepository.save(classifica); // 🔁 salva Classifica con gli elementi
    }

    model.addAttribute("classifica", classifica);
    model.addAttribute("partecipazioni", partecipazioneRepository.findAll());

    return "classifica/insClassifica.html";
}


@Autowired
private EntityManager entityManager;


@PostMapping("/saveClassifica")
public String saveClassifica(@ModelAttribute("classifica") Classifica classifica,
                             @RequestParam("edizioneId") Long edizioneId,
                             Model model) {

    Edizione edizionePersistita = edizioneRepository.findById(edizioneId).orElse(null);
    if (edizionePersistita == null) {
        return "errore";
    }

    classifica.setEdizione(edizionePersistita);
    classifica.setEdizioneId(edizionePersistita.getId());

    if (classifica.getElementiClassifica() != null) {
        for (ElementoClassifica el : classifica.getElementiClassifica()) {
            el.setClassifica(classifica); // 🔄 relaziona ogni elemento
        }
    }

    // 🧠 Salva classifica con tutti gli elementi in cascata
    classificaService.save(classifica);
    
    return "redirect:/classifica/" + edizionePersistita.getId();
}



}
