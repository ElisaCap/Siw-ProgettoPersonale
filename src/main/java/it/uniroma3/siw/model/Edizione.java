package it.uniroma3.siw.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Edizione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	private LocalDate data;
	private String urlImmagine;
	@OneToMany(mappedBy = "edizione")
	private List<Partecipazione>partecipazioni;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Classifica classifica;
	
	
	public Classifica getClassifica() {
		return classifica;
	}
	public void setClassifica(Classifica classifica) {
		this.classifica = classifica;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	public List<Partecipazione> getPartecipazioni() {
		return partecipazioni;
	}
	public void setPartecipazioni(List<Partecipazione> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}
	@Override
	public int hashCode() {
		return Objects.hash(data, id, partecipazioni, urlImmagine);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edizione other = (Edizione) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id)
				&& Objects.equals(partecipazioni, other.partecipazioni)
				&& Objects.equals(urlImmagine, other.urlImmagine);
	}
	
	
	
	
	
}
