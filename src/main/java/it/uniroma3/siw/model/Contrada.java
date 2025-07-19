package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contrada {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	@NotBlank(message = "campo obbligatorio")
private String nome;
	@NotBlank(message = "campo obbligatorio")
private String descrizione;
@Column(length=3000)
@NotBlank(message = "campo obbligatorio")
private String descrizioneLunga; 
@OneToMany(mappedBy = "contrada")
private List<Partecipazione>partecipazioni;


@Lob
private byte[] urlImmagine; // campo per l’immagine
@Transient
private MultipartFile fileImmagine;

// getter e setter per fileImmagine
public MultipartFile getFileImmagine() {
    return fileImmagine;
}

public void setFileImmagine(MultipartFile fileImmagine) {
    this.fileImmagine = fileImmagine;
}






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
public String getDescrizione() {
	return descrizione;
}
public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}


@Override
public int hashCode() {
	return Objects.hash(descrizione, id, nome, urlImmagine);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Contrada other = (Contrada) obj;
	return Objects.equals(descrizione, other.descrizione) && Objects.equals(id, other.id)
			&& Objects.equals(nome, other.nome) && Objects.equals(urlImmagine, other.urlImmagine);
}
public String getDescrizioneLunga() {
	return descrizioneLunga;
}

public void setDescrizioneLunga(String descrizioneLunga) {
	this.descrizioneLunga = descrizioneLunga;
}





}
