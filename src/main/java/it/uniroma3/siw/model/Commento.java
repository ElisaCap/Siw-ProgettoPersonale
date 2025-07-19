package it.uniroma3.siw.model;

import java.util.Objects;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Commento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
private String utente;
private String contenuto;
@ManyToOne
private Edizione edizione;
@ManyToOne
private User user;




public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUtente() {
	return utente;
}
public void setUtente(String utente) {
	this.utente = utente;
}
public String getContenuto() {
	return contenuto;
}
public void setContenuto(String contenuto) {
	this.contenuto = contenuto;
}
@Override
public int hashCode() {
	return Objects.hash(contenuto, id, utente);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Commento other = (Commento) obj;
	return Objects.equals(contenuto, other.contenuto) && Objects.equals(id, other.id)
			&& Objects.equals(utente, other.utente);
}
public Edizione getEdizione() {
	return edizione;
}
public void setEdizione(Edizione edizione) {
	this.edizione = edizione;
}







}
