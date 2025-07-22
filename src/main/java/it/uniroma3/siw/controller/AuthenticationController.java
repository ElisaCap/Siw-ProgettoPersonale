package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	    	model.addAttribute("credentials", new Credentials());

	 	    return "formRegisterUser.html";
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
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
	        return "paginaIntroduttiva.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/indexAdmin.html";
			}
		}*/
        return "homepage.html";
	}
		
	@GetMapping("/success")
	public String defaultAfterLogin() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	            return "homepage.html";
	        }
	    }

	    return "homepage.html";
	}


	@PostMapping(value = { "/register" })
	public String registerUser(@Valid @ModelAttribute("user") User user,
	                           BindingResult userBindingResult,
	                           @Valid @ModelAttribute("credentials") Credentials credentials,
	                           BindingResult credentialsBindingResult,
	                           Model model) {

	    // Verifica username già esistente
	    if (credentialsService.existsByUsername(credentials.getUsername())) {
	        credentialsBindingResult.rejectValue("username", "error.credentials", "Username già in uso. Scegli un altro nome.");
	    }

	    if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {

	        // Sincronizzo il name di user con username scelto
	        user.setName(credentials.getUsername());

	        credentials.setUser(user);
	        credentials.setRole("ROLE_USER");

	        try {
	            credentialsService.saveCredentials(credentials);
	        } catch (DataIntegrityViolationException e) {
	            credentialsBindingResult.rejectValue("username", "error.credentials", "Errore di salvataggio: username già registrato.");
	            return "formRegisterUser.html";
	        }
	        
	        model.addAttribute("user", user);
	        return "registrationSuccessful.html";
	    }

	    return "formRegisterUser.html";
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    var auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }return "redirect:/home";
	}
	
	
	}}
	

