package it.uniroma3.siw.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Fantino {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	@NotBlank(message = "campo obbligatorio")
private String nome;
	@NotBlank(message = "campo obbligatorio")
private String cognome;
	@NotNull(message = "campo obbligatorio")
private LocalDate dataNascita;
@OneToMany(mappedBy = "fantino" ,cascade = CascadeType.REMOVE, orphanRemoval = true)
private List<Partecipazione>partecipazioni;

@Transient
private MultipartFile fileImmagine;

//getter e setter per fileImmagine
public MultipartFile getFileImmagine() {
 return fileImmagine;
}

public void setFileImmagine(MultipartFile fileImmagine) {
 this.fileImmagine = fileImmagine;
}


@Lob
private byte[] urlImmagine; // campo per l’immagine



public void setUrlImmagine(byte[] urlImmagine) {
	this.urlImmagine = urlImmagine;
}



public byte[] getUrlImmagine() {
	return urlImmagine;
}

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


public LocalDate getDataNascita() {
	return dataNascita;
}
public void setDataNascita(LocalDate dataNascita) {
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
