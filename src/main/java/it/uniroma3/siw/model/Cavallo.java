package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cavallo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String nome;
private String razza;
private String urlImmagine;
@OneToMany(mappedBy = "cavallo")
private List<Partecipazione>partecipazioni;

public List<Partecipazione> getPartecipazioni() {
	return partecipazioni;
}
public void setPartecipazioni(List<Partecipazione> partecipazioni) {
	this.partecipazioni = partecipazioni;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getRazza() {
	return razza;
}
public void setRazza(String razza) {
	this.razza = razza;
}
public String getUrlImmagine() {
	return urlImmagine;
}
public void setUrlImmagine(String urlImmagine) {
	this.urlImmagine = urlImmagine;
}
@Override
public int hashCode() {
	return Objects.hash(id, nome, razza, urlImmagine);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cavallo other = (Cavallo) obj;
	return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(razza, other.razza)
			&& Objects.equals(urlImmagine, other.urlImmagine);
}




}
