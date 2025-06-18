package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.service.CavalloService;

@Controller
public class CavalloController {

    @Autowired
    private CavalloService cavalloService;

    @GetMapping("/insCavallo")
    public String insCavallo1(Model model) {
        model.addAttribute("cavallo", new Cavallo());
        return "cavallo/insCavallo.html";
    }

    @PostMapping("/insCavallo")
    public String salvaCavallo(@ModelAttribute("cavallo") Cavallo cavallo, Model model) {
      
        MultipartFile file = cavallo.getFileImmagine();
        if (file != null && !file.isEmpty()) {
            try {
				cavallo.setImmagine(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        this.cavalloService.save(cavallo);
        return "cavallo/cavallo.html";
    }

    @GetMapping("/trovaCavallo")
    public String mostraForm() {
        return "cavallo/trovacavallo.html";
    }

    @GetMapping("/cavalli/{id}")
    public String cercaPerId(@PathVariable Long id, Model model) {
        Cavallo cavallo = this.cavalloService.getCavalloById(id);
        model.addAttribute("cavallo", cavallo);
        return "cavallo/cavallo.html";
    }

    @GetMapping("/cavalli/cercatutti")
    public String cercatutti(Model model) {
        Iterable<Cavallo> cavalli = this.cavalloService.getAll();
        model.addAttribute("cavalli", cavalli);
        return "cavallo/cavalli.html";
    }

    @GetMapping("/cavalli/{id}/immagine")
    @ResponseBody
    public ResponseEntity<byte[]> getImmagine(@PathVariable Long id) {
        Cavallo cavallo = cavalloService.getCavalloById(id);
        if (cavallo == null || cavallo.getImmagine() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Usa IMAGE_PNG se il file è PNG

        return new ResponseEntity<>(cavallo.getImmagine(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/cavalli/delete/{id}")
	  public String eliminaFantino(@PathVariable Long id, Model model) {
	      cavalloService.deleteById(id);
	      model.addAttribute("fantini", cavalloService.getAll());
	      return "cavallo/cavalli.html"; // oppure redirect a cercatutti
	  }
    
    
    
    
    
    
    
    
    
}
