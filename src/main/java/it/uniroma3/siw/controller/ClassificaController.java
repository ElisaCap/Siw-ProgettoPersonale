package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
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
import it.uniroma3.siw.model.Partecipazione;
import it.uniroma3.siw.repository.ClassificaRepository;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.service.ClassificaService;
import it.uniroma3.siw.service.EdizioneService;
import it.uniroma3.siw.service.ElementoClassificaService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

@Controller
public class ClassificaController {

    private final ElementoClassificaService elementoClassificaService;
	
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
@Autowired
private EdizioneService edizioneSerivice;

    ClassificaController(ElementoClassificaRepository elementoClassificaRepository, EdizioneRepository edizioneRepository, ElementoClassificaService elementoClassificaService) {
        this.elementoClassificaRepository = elementoClassificaRepository;
        this.edizioneRepository = edizioneRepository;
        this.elementoClassificaService = elementoClassificaService;
    }
    
    @GetMapping("/classifica/{id}")
    public String classificaId(@PathVariable Long id, Model model) {
        Edizione edizione = edizioneSerivice.getById(id);
        Classifica classifica = classificaService.getOrCreateByEdizione(edizione);
        
        model.addAttribute("classifica", classifica);
        model.addAttribute("elementiClassifica", elementoClassificaRepository.findAllByClassificaOrderByPosizione(classifica));
        model.addAttribute("partecipazioni",partecipazioneRepository.findByEdizioneId(id));
        return "classifica/classifica.html"; // il tuo template HTML
    }


/*
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/insEdizioneClassifica")
public String insEdizione(Model model) {
	//Classifica classifica=new Classifica();
	//model.addAttribute("classifica", classifica);
	  model.addAttribute("edizioni", edizioneRepository.findAll());
      return "classifica/insEdizione.html";
}*/

    
    /*
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping("/saveEdizioneClassifica")
public String saveEdizioneClassifica(@RequestParam("edizioneId") Long edizioneId, Model model) {
    Optional<Edizione> edizioneOpt = edizioneRepository.findById(edizioneId);
    
    if (edizioneOpt.isEmpty()) {
        return "redirect:/insEdizioneClassifica?errore=edizione_non_valida";
    }

    Edizione edizione = edizioneOpt.get();

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
*/

/*
@PostMapping("/saveNumContrade")
public String setNumContrade(
		@RequestParam("edizioneId") Long edizioneId,@ModelAttribute("classifica") Classifica classifica,
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
    		model.addAttribute("partecipazioni", partecipazioneRepository.findByEdizioneId(edizioneId));
    model.addAttribute("num", num);
    return "classifica/insClassifica.html";
}
*/




@Autowired
private EntityManager entityManager;
/*
@PreAuthorize("hasRole('ADMIN')")
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
}*/

@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/classifiche/delete/{id}")
public String eliminaClassifica(@PathVariable Long id, Model model) {
    classificaService.deleteById(id);
    model.addAttribute("classifiche", classificaService.getAll());
    return "classifica/classifiche.html"; // oppure redirect a cercatutti
}
/*
@GetMapping("/modificaClassifica/{id}")
public String modificaClassifica(@PathVariable Long id, Model model) {
    Classifica classifica=classificaService.getById(id);
    model.addAttribute("classifica",classifica);
    model.addAttribute("id",id);
    List<Partecipazione> partecipazioni = partecipazioneRepository.findByEdizioneId(id);
    List<Partecipazione> partecipazioni2=new ArrayList<>();
    for(Partecipazione p:partecipazioni) {
    	if(p.getElementoClassifica()==null)
    	partecipazioni2.add(p);
    }
    model.addAttribute("partecipazioni", partecipazioni2);
    return "classifica/modificaClassifica.html"; // oppure redirect a cercatutti
}*/
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/eliminaElementoClassifica/{id}/{idc}")
public String eliminaElemento(@PathVariable Long id,
                              @PathVariable Long idc,
                              Model model) {

    System.out.println(">> Tentativo di eliminazione ElementoClassifica con id = " + id + " e classifica = " + idc);

    elementoClassificaService.deleteById(id);

    model.addAttribute("classifica", classificaService.getById(idc));
    model.addAttribute("elementiClassifica",
            elementoClassificaRepository.findAllByClassificaOrderByPosizione(classificaService.getById(idc)));
    model.addAttribute("partecipazioni",classificaService.getById(idc).getEdizione().getPartecipazioni());

    return "redirect:/classifica/"+idc;
}


@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/insElemento/{id}")
public String insElemento(@RequestParam(name = "posizione", defaultValue = "0") int pos,
                          @PathVariable Long id,
                          Model model) {
    Classifica classifica = classificaService.getById(id);
   
    model.addAttribute("partecipazioni", partecipazioneRepository.findByEdizioneId(classifica.getEdizioneId()));
    model.addAttribute("classifica", classifica);
    model.addAttribute("posizione", pos + 1); // se vuoi aumentarla
    model.addAttribute("id", id);
    model.addAttribute("elementoClassifica", new ElementoClassifica());

    return "classifica/insElemento.html";
}

@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/salvaElemento/{id}")
@Transactional
public String salvaElemento(@RequestParam("partecipazioneId") Long partecipazioneId,
                            @RequestParam("posizione") int posizione,
                            @PathVariable Long id,Model model) {
    Classifica classifica = classificaService.getById(id);
    Partecipazione partecipazione = partecipazioneRepository.findById(partecipazioneId).orElseThrow();

    ElementoClassifica elemento = new ElementoClassifica();
    elemento.setClassifica(classifica);
    elemento.setPartecipazione(partecipazione);
    elemento.setPosizione(posizione );
    elementoClassificaRepository.incrementaPosizioniDa(posizione,elemento.getClassifica().getEdizioneId());
    elementoClassificaRepository.save(elemento);
    model.addAttribute("partecipazioni",classifica.getEdizione().getPartecipazioni());
    return "redirect:/classifica/" + id;
}






}
