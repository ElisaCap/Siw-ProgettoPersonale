package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }

	public User findById(Long idUser) {
		return this.userRepository.findById(idUser).orElse(null);
	}

	public User getUserByUsername(String username) {
		return this.userRepository.findByName(username);
	}
    
    
	


    @Autowired
    private CredentialsRepository credentialsRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void inizializza() {
        List<String[]> utenti = List.of(
            new String[]{"mario", "rossi", "mario.rossi@example.com", "marior", "password123", Credentials.DEFAULT_ROLE},
            new String[]{"luca", "bianchi", "luca.bianchi@example.com", "lucab", "pass456", Credentials.DEFAULT_ROLE},
            new String[]{"giulia", "verdi", "giulia.verdi@example.com", "giuliav", "verde789", Credentials.DEFAULT_ROLE},
            new String[]{"federico", "neri", "federico.neri@example.com", "fedeneri", "fede2024", Credentials.DEFAULT_ROLE},
            new String[]{"anna", "bruni", "anna.bruni@example.com", "annab", "annaPass!", Credentials.DEFAULT_ROLE},
            new String[]{"paolo", "conti", "paolo.conti@example.com", "paoloc", "conti$123", Credentials.DEFAULT_ROLE},
            new String[]{"chiara", "marini", "chiara.marini@example.com", "chiaram", "chiaraPwd", Credentials.DEFAULT_ROLE},
            new String[]{"gabriele", "ferri", "gabriele.ferri@example.com", "gabferri", "gab2025", Credentials.DEFAULT_ROLE},
            new String[]{"ilaria", "greco", "ilaria.greco@example.com", "ilariag", "greco@456", Credentials.DEFAULT_ROLE},
            new String[]{"francesco", "martini", "francesco.martini@example.com", "frankm", "martini123", Credentials.DEFAULT_ROLE}
        );

        for (String[] u : utenti) {
            String nome = u[0];
            String cognome = u[1];
            String email = u[2];
            String username = u[3];
            String password = u[4];
            String ruolo = u[5];

            // Evita duplicati
            if (credentialsRepository.findByUsername(username).isPresent())
                continue;

            // 1. Crea e salva User
            User user = new User();
            user.setName(nome);
            user.setSurname(cognome);
            user.setEmail(email);
            user = userRepository.save(user); // importante!

            // 2. Crea e salva Credentials
            Credentials credentials = new Credentials();
            credentials.setUsername(username);
            credentials.setPassword(passwordEncoder.encode(password));
            credentials.setRole(ruolo);
            credentials.setUser(user);

            credentialsRepository.save(credentials);
        }
    }

	
	
    
    
}
