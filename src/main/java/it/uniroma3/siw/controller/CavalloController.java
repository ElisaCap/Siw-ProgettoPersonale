package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.repository.CavalloRepository;
import it.uniroma3.siw.service.CavalloService;

@Controller
public class CavalloController {
    @Autowired
    private  CavalloRepository cavalloRepository;

    @Autowired
    private CavalloService cavalloService;

   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/insCavallo")
    public String insCavallo1(Model model) {
        model.addAttribute("cavallo", new Cavallo());
        return "cavallo/insCavallo.html";
    }
    @PreAuthorize("hasRole('ADMIN')")
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
        model.addAttribute("cavalli",cavalloService.getAll());
        return "redirect:/cavalli/cercatutti";
    }

  
    @GetMapping("/cavalli/{id}")
    public String cercaPerId(@PathVariable Long id, Model model) {
        Cavallo cavallo = this.cavalloService.getCavalloById(id);
        model.addAttribute("cavallo", cavallo);
        return "cavallo/cavallo.html";
    }

    @GetMapping("/cavalli/cercatutti")
    public String cercatutti(Model model) {
        List<Cavallo> cavalli = (List)this.cavalloService.getAll();
        model.addAttribute("cavalli", cavalli);
        model.addAttribute("dim",cavalli.size());
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cavalli/delete/{id}")
	  public String eliminaCavallo(@PathVariable Long id, Model model) {
	      cavalloService.deleteById(id);
	      model.addAttribute("cavalli", cavalloService.getAll());
	      return "redirect:/cavalli/cercatutti"; 
	  }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cavalli/modifica/{id}")
    public String modifica(@PathVariable Long id,Model model) {
    	Cavallo cavallo=this.cavalloService.getCavalloById(id);
    	model.addAttribute("cavallo",cavallo);
    	model.addAttribute("id",id);
    	return "cavallo/modificaCavallo.html";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveCavallo")
    public String modificaCavallo(@ModelAttribute("cavallo") Cavallo cavalloModificato, Model model) {
        Cavallo cavalloEsistente = cavalloService.getCavalloById(cavalloModificato.getId());

        // Aggiorna solo i campi modificabili
        cavalloEsistente.setNome(cavalloModificato.getNome());
        cavalloEsistente.setRazza(cavalloModificato.getRazza());

        MultipartFile file = cavalloModificato.getFileImmagine();
        if (file != null && !file.isEmpty()) {
            try {
                cavalloEsistente.setImmagine(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ⚠️ NON toccare cavalloEsistente.getPartecipazioni()!

        cavalloService.save(cavalloEsistente);
        return "redirect:/cavalli/cercatutti";
    }

    
    
    
    
    
    
    
}
