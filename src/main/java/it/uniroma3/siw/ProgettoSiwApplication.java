package it.uniroma3.siw;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.authentication.AuthConfiguration;
import it.uniroma3.siw.model.Cavallo;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;

@SpringBootApplication
public class ProgettoSiwApplication implements ApplicationRunner {
	  @Autowired
	    private CredentialsService credentialsService;
	   @Autowired
private AuthConfiguration authConfiguration;

	public static void main(String[] args) {
	
		
		SpringApplication.run(ProgettoSiwApplication.class, args);
	}
	
	   public void run(ApplicationArguments args) throws Exception {
	    
	      this.authConfiguration.run();
	    }
	
	
	

}
