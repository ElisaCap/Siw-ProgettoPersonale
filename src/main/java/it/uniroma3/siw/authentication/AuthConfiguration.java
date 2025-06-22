package it.uniroma3.siw.authentication;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfiguration {
	private static final String DEFAULT_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
@Autowired
private CredentialsRepository credentialsRepository;
    private final DataSource dataSource;

    public AuthConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    
    
    
    
    public void run() throws Exception {
        String defaultUsername = "ElisaCaprio";
        String defaultPassword = "password"; // NB: criptala prima di salvarla!
        
        // Se esiste già, non fare nulla
        if (credentialsRepository.findByUsername(defaultUsername).isEmpty()) {
            Credentials admin = new Credentials();
            admin.setUsername(defaultUsername);
            admin.setPassword(passwordEncoder().encode(defaultPassword)); // Criptare la password!
            admin.setRole("ROLE_ADMIN");
            credentialsRepository.save(admin);
            System.out.println("Admin creato con username: " + defaultUsername);
        } else {
            System.out.println("Admin già presente, nessuna azione necessaria.");
        }
    }

  
    

    
    
    
    
    
    
    
    
    
    
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/","/paginaIntroduttiva.html",
            			    "/login", "/register","/insCavallo", "/logout",
            			    "/cavallo/**","/edizione/**", "/classifica/**","/contrade/**",
            			    "/home","/homepage.html","/visualizza",
            			    "/cavallo/trovacavallo.html","/cavalli/cercatutti",
            			    "/css/**", "/js/**", "/images/**", "/sfondo.jpg", "/webjars/**","/immagginicontrade/**"
            			).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/success", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/403")
            );

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
            "SELECT username, password, enabled FROM credentials WHERE username=?");
        manager.setAuthoritiesByUsernameQuery(
            "SELECT username, role FROM credentials WHERE username=?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    


}
