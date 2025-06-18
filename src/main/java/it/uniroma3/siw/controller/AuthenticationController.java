package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Controller
	public class UserController {

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	    	
	        model.addAttribute("user", new User());
	        return "formRegisterUser.html"; // The name of your template file (registration.html)
	    }

	 

	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		//credentialsService.setAdmin();
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
	        System.out.println("Utente già autenticato: " + authentication.getName());
	        return "redirect:/success";  // Se l'utente è già autenticato, redirigi alla pagina di successo
	    }
	    return "formLogin.html";  // Altrimenti mostra la pagina di login
	}
	/*
	@GetMapping("/success")
	public String successPage() {
	    return "success.html";
	}*/

	
	
	
	
	@GetMapping("/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
	        return "paginaIntroduttiva.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/indexAdmin.html";
			}
		}
        return "paginaIntroduttiva.html";
	}
		
	@GetMapping("/success")
	public String defaultAfterLogin() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	            return "successAdmin.html";
	        }
	    }

	    return "successUser.html";
	}



	@PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("user") User user,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            credentials.setUser(user);
            credentials.setUsername(user.getName());
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterUser.html";
    }
	}
	
	@GetMapping("/logout")
	public String getMethodName(Model model) {
		return "logout.html";
	}
	
	
	
	
}
