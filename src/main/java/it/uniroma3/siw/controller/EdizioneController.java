package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Classifica;
import it.uniroma3.siw.model.Edizione;
import it.uniroma3.siw.model.ElementoClassifica;
import it.uniroma3.siw.repository.EdizioneRepository;
import it.uniroma3.siw.repository.ElementoClassificaRepository;
import it.uniroma3.siw.service.ClassificaService;
import it.uniroma3.siw.service.EdizioneService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.model.User;

@Controller
public class EdizioneController {
	@Autowired
private EdizioneService edizioneSerivice;
	@Autowired
	private EdizioneRepository edizioneRepository;
	@Autowired
	private ClassificaService classificaService;
	@Autowired
	private ElementoClassificaRepository elementoClassificaRepository;
	@Autowired
	private EdizioneService edizioneService;
	@Autowired
	private UserService userService;
	
@GetMapping("/edizioni")
public String edizioni(Model model) {
	model.addAttribute("edizioni",this.edizioneSerivice.getAll());
	return "edizione/edizioni.html";
}
@GetMapping("/edizione/{id}")
public String edizione(@PathVariable Long id, Model model) {
	model.addAttribute("edizione",this.edizioneSerivice.getById(id));
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
	        String username = auth.getName();
	        User user = userService.getUserByUsername(username);
	        model.addAttribute("userLoggato", user);
	    } else {
	        model.addAttribute("userLoggato", null);
	    }


	return "edizione/edizione.html";
}




@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/insEdizione")
public String insEdizione(Model model) {
	Edizione edizione=new Edizione();
	model.addAttribute("minDate", LocalDate.of(1900, 7, 1));
	model.addAttribute("maxDate", LocalDate.now().plusDays(30));

	model.addAttribute("edizione",edizione);
	return "edizione/nuovaEdizione.html";
}

@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/insEdizione")
public String saveEdizione(@ModelAttribute("edizione") Edizione edizione
		
                           //,@RequestParam("fileImmagine") MultipartFile file)
		)
		{
    // Associa classifica
    Classifica classifica = new Classifica();
    classifica.setEdizione(edizione);
    edizione.setClassifica(classifica);

    MultipartFile file = edizione.getFileImmagine();
    if (file != null && !file.isEmpty()) {
        try {
			edizione.setUrlImmagine(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Salva tutto
    edizioneSerivice.save(edizione);

    return "redirect:/edizione/" + edizione.getId();
}



@GetMapping("/getClassifica/{id}")
public String classificaEdId(@RequestParam Long id,Model model) {
	Classifica classifica=this.classificaService.getById(id);
	model.addAttribute("classifica",classifica);
	model.addAttribute("elementiClassifica",this.elementoClassificaRepository.findAllByClassificaOrderByPosizione(classifica));
	 if (id == null) {
         return "errore"; // Gestisci un eventuale errore
     }
	return "redirect:/classifica/"+ id;
	
}

@GetMapping("/edizioniCavallo/{id}")
public String edizioneCavallo(@PathVariable Long id, Model model) {
    Iterable<Edizione> edizioni = edizioneRepository.findEdizioniByCavalloId(id);
    model.addAttribute("edizioni", edizioni);
    return "edizione/edizioni";
}
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/edizioni/delete/{id}")
public String eliminaEdizione(@PathVariable Long id, Model model) {
    edizioneService.deleteById(id);
    model.addAttribute("edizioni", edizioneService.getAll());
    return "redirect:/edizioni"; // oppure redirect a cercatutti
}


@GetMapping("/edizione/{id}/immagine")
@ResponseBody
public ResponseEntity<byte[]> getImmagine(@PathVariable Long id) {
    Edizione edizione = edizioneService.getById(id);
    if (edizione == null || edizione.getUrlImmagine() == null) {
        return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG); // Usa IMAGE_PNG se il file è PNG

    return new ResponseEntity<>(edizione.getUrlImmagine(), headers, HttpStatus.OK);
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/modificaEdizione/{id}")
public String modifica(Model model, @PathVariable Long id) {
	Edizione edizione=edizioneSerivice.getById(id);
	model.addAttribute("minDate", LocalDate.of(1900, 7, 1));
	model.addAttribute("maxDate", LocalDate.now().plusDays(30));
	model.addAttribute("edizione",edizione);
	return "edizione/modificaEdizione.html";
}

@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/salvaEdizione")
@Transactional
public String salvaEdizione(@ModelAttribute("edizione") Edizione edizione) {
    MultipartFile file = edizione.getFileImmagine();

    if (file != null && !file.isEmpty()) {
        try {
            edizione.setUrlImmagine(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1️⃣ Estraggo la classifica e la scollego
    Classifica classifica = edizione.getClassifica();
    edizione.setClassifica(null); // importante

    // 2️⃣ Salvo Edizione senza Classifica (così ottiene l'ID)
    edizione = edizioneRepository.save(edizione); // assegna ID

    // 3️⃣ Se la Classifica esiste, la collego e la salvo separatamente
    if (classifica != null) {
        classifica.setEdizione(edizione); // imposta Edizione con ID
        classificaService.save(classifica); // ora può usare il MapsId
    }

    // 4️⃣ Se vuoi, ricollega la classifica (opzionale)
    edizione.setClassifica(classifica);
    edizioneSerivice.save(edizione); // salva nuovamente solo se necessario

    return "redirect:/edizione/" + edizione.getId();
}


}