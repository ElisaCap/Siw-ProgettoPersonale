package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.service.ClassificaService;

@Controller
public class ClassificaController {

    private final EdizioneRepository edizioneRepository;

    private  ElementoClassificaRepository elementoClassificaRepository;
@Autowired
private ClassificaService classificaService;

    ClassificaController(ElementoClassificaRepository elementoClassificaRepository, EdizioneRepository edizioneRepository) {
        this.elementoClassificaRepository = elementoClassificaRepository;
        this.edizioneRepository = edizioneRepository;
    }
@GetMapping("/classifica/{id}")
public String classificaId(@RequestParam Long id,Model model) {
	model.addAttribute("classifica",this.classificaService.getById(id));
	model.addAttribute("elementiClassifica",elementoClassificaRepository.findAllByClassifica(classificaService.getById(id)));
	
	return "classifica/classifica.html";
	
}
}
