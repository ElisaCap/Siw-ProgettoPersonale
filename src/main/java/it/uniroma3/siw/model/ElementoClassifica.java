package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class ElementoClassifica {
private int posizione;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;



@ManyToOne
@JoinColumn(name = "classifica_id")
private Classifica classifica;

@OneToOne
@JoinColumn(name = "partecipazione_id", unique = true)
private Partecipazione partecipazione;







public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}


public int getPosizione() {
	return posizione;
}
public void setPosizione(int posizione) {
	this.posizione = posizione;
}
public Classifica getClassifica() {
	return classifica;
}
public void setClassifica(Classifica classifica) {
	this.classifica = classifica;
}
public Partecipazione getPartecipazione() {
	return partecipazione;
}
public void setPartecipazione(Partecipazione partecipazione) {
	this.partecipazione = partecipazione;
}


}
