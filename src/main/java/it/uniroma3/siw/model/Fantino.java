package it.uniroma3.siw.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fantino {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String nome;
private String cognome;
private String urlImmagine;
private Date dataNascita;
@OneToMany(mappedBy = "fantino")
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
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getUrlImmagine() {
	return urlImmagine;
}
public void setUrlImmagine(String urlImmagine) {
	this.urlImmagine = urlImmagine;
}
public Date getDataNascita() {
	return dataNascita;
}
public void setDataNascita(Date dataNascita) {
	this.dataNascita = dataNascita;
}
@Override
public int hashCode() {
	return Objects.hash(cognome, dataNascita, id, nome, urlImmagine);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Fantino other = (Fantino) obj;
	return Objects.equals(cognome, other.cognome) && Objects.equals(dataNascita, other.dataNascita)
			&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
			&& Objects.equals(urlImmagine, other.urlImmagine);
}





}
