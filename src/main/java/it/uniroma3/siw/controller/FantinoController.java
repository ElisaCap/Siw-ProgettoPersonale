package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Fantino;
import it.uniroma3.siw.service.FantinoService;

@Controller
public class FantinoController {
	@Autowired
private FantinoService fantinoService;
	
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/insFantino")
	public String insfantino(Model model) {
		model.addAttribute("fantino", new  Fantino());
		model.addAttribute("minDate", LocalDate.of(1950, 7, 1));
		model.addAttribute("maxDate", LocalDate.now().minusYears(16));
		return "fantino/insFantino.html";
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insFantino")
	public String salvafantino(@ModelAttribute("fantino") Fantino fantino, Model model) {
	    MultipartFile file = fantino.getFileImmagine();
        if (file != null && !file.isEmpty()) {
            try {
				fantino.setUrlImmagine(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    
	    this.fantinoService.save(fantino);
	    model.addAttribute(fantino);

	    return "redirect:/fantini/cercatutti";
	}

	
	
	 
	 
	 
	  @GetMapping("/fantini/{id}")
	    public String cercaPerId(@PathVariable Long id, Model model) {
	        Fantino fantino = this.fantinoService.getByid(id);
	        model.addAttribute("fantino", fantino);
	        return "fantino/fantino.html";
	    }
	  
	  
	  @GetMapping("/fantini/cercatutti")
	    public String cercatutti( Model model) {
		    Iterable<Fantino> fantini=this.fantinoService.getAll();
	        model.addAttribute("fantini", fantini);
	        return "fantino/fantini.html";
	    }
	  
	  
	  
	    @PreAuthorize("hasRole('ADMIN')")
	  @GetMapping("/fantini/delete/{id}")
	  public String eliminaFantino(@PathVariable Long id, Model model) {
	      fantinoService.deleteById(id);
	      model.addAttribute("fantini", fantinoService.getAll());
	      return "fantino/fantini.html"; // oppure redirect a cercatutti
	  }
	  
	  
	
	  
	  @GetMapping("/fantini/{id}/immagine")
	  @ResponseBody
	  public ResponseEntity<byte[]> getImmagine(@PathVariable Long id) {
	      Fantino fantino = fantinoService.getByid(id);
	      if (fantino == null || fantino.getUrlImmagine() == null) {
	          return ResponseEntity.notFound().build();
	      }

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.IMAGE_JPEG); // oppure rileva il tipo dinamicamente
	      return new ResponseEntity<>(fantino.getUrlImmagine(), headers, HttpStatus.OK);
	  }
	  
	    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fantini/modifica/{id}")
    public String modifica(@PathVariable Long id,Model model) {
    	model.addAttribute("fantino",this.fantinoService.getByid(id));
    	model.addAttribute("id",id);
    	model.addAttribute("minDate", LocalDate.of(1950, 7, 1));
		model.addAttribute("maxDate", LocalDate.now().minusYears(16));
    	return "fantino/modificaFantino.html";
    }
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/salvaFantino")
    public String salvaFantino(@ModelAttribute("fantino")Fantino fantino,Model model) {
		Fantino fantinoc=fantinoService.getByid(fantino.getId());
		fantinoc.setCognome(fantino.getCognome());
		fantinoc.setDataNascita(fantino.getDataNascita());
		fantinoc.setNome(fantino.getNome());
    	  MultipartFile file = fantino.getFileImmagine();
    	  fantinoc.setFileImmagine(file);
          if (file != null && !file.isEmpty()) {
              try {
  				fantino.setUrlImmagine(file.getBytes());
  	          fantinoc.setUrlImmagine(file.getBytes());

  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }
    	this.fantinoService.save(fantinoc);
    	model.addAttribute("fantini",this.fantinoService.getAll());
    	return "fantino/fantini.html";
    	
    }
	  
	  
	  
	  
}
