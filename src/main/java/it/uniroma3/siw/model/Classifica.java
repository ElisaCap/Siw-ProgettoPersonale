package it.uniroma3.siw.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.repository.cdi.Eager;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Classifica {
	 @Id
	    private Long edizioneId;

	
	
@OneToOne
@MapsId
private Edizione edizione;


@OneToMany(mappedBy = "classifica", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ElementoClassifica> elementiClassifica;






public Long getEdizioneId() {
	return edizioneId;
}
public void setEdizioneId(Long edizioneId) {
	this.edizioneId = edizioneId;
}
public List<ElementoClassifica> getElementiClassifica() {
	return elementiClassifica;
}
public void setElementiClassifica(List<ElementoClassifica> elementiClassifica) {
	this.elementiClassifica = elementiClassifica;
}
public Edizione getEdizione() {
	return edizione;
}
public void setEdizione(Edizione edizione) {
	this.edizione = edizione;
}
@Override
public int hashCode() {
	return Objects.hash(edizione, elementiClassifica);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Classifica other = (Classifica) obj;
	return Objects.equals(edizione, other.edizione) && Objects.equals(elementiClassifica, other.elementiClassifica);
}







}
