package it.uniroma3.siw.model;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Partecipazione {
@Id
@GeneratedValue(strategy = GenerationType.AUTO )
private Long id;
@ManyToOne
private Contrada contrada;
@ManyToOne
private Fantino fantino;
@ManyToOne
private Cavallo cavallo;
@ManyToOne
private Edizione edizione;





public Edizione getEdizione() {
	return edizione;
}
public void setEdizione(Edizione edizione) {
	this.edizione = edizione;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public Contrada getContrada() {
	return contrada;
}
public void setContrada(Contrada contrada) {
	this.contrada = contrada;
}
public Fantino getFantino() {
	return fantino;
}
public void setFantino(Fantino fantino) {
	this.fantino = fantino;
}
public Cavallo getCavallo() {
	return cavallo;
}
public void setCavallo(Cavallo cavallo) {
	this.cavallo = cavallo;
}
@Override
public int hashCode() {
	return Objects.hash(cavallo, contrada, fantino, id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Partecipazione other = (Partecipazione) obj;
	return Objects.equals(cavallo, other.cavallo) && Objects.equals(contrada, other.contrada)
			 && Objects.equals(fantino, other.fantino)
			&& Objects.equals(id, other.id);
}



}
