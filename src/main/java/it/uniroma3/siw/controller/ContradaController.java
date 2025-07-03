package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.service.ContradaService;

@Controller
public class ContradaController {

    @Autowired
    private ContradaService contradaService;

    @GetMapping("/urlImmagine/{id}")
    public ResponseEntity<byte[]> getImmagine(@PathVariable("id") Long id) {
        Contrada contrada = contradaService.getByid(id);
        if (contrada == null || contrada.getUrlImmagine() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Cambia se l'immagine è JPEG

        return new ResponseEntity<>(contrada.getUrlImmagine(), headers, HttpStatus.OK);
    }

    @GetMapping("/contrade")
    public String mostraTutte(Model model) {
        model.addAttribute("contrade", this.contradaService.getAll());
        return "contrade/contrade.html";
    }

    @GetMapping("/contrade/{id}")
    public String mostraContrada(@PathVariable("id")Long id, Model model) {
        Contrada contrada = this.contradaService.getByid(id);
        model.addAttribute("contrada", contrada);
        return "contrade/contrada.html";
    }
    @GetMapping("/eliminaContrada/{id}")
    public String eliminaContrada(@PathVariable("id")Long id, Model model) {
    	contradaService.deleteById(id);
    	model.addAttribute("contrade", this.contradaService.getAll());
    	return "contrade/contrade.html";
    }
    
}
