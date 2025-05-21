package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
    
    
   
    
   
    
    
    
    
    
    
    
    public void setAdmin() {
    	Credentials credentials=new Credentials();
    	credentials.setId(1l);
    	credentials.setEnabled(true);
    	credentials.setRole("ADMIN");
    	credentials.setPassword(passwordEncoder.encode("el1sa2003"));
    	this.credentialsRepository.save(credentials);
    }
    
    
    
    public void makeAdminById(Long id) {
        Credentials credentials = credentialsRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        credentials.setRole("ROLE_ADMIN");
        credentialsRepository.save(credentials);
    }
}
    
    
    
    
    
    
    
    
    

