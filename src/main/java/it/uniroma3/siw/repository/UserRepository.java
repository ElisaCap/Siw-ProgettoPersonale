package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.User;
import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional
	public User findByName(String name);

	//public User findByUsername(String username);

}
