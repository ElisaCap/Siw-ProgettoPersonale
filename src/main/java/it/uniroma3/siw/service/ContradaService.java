package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.repository.ContradaRepository;

@Service
public class ContradaService {
@Autowired
private ContradaRepository contradaRepository;
	public Iterable<Contrada> getAll(){
		return contradaRepository.findAll();
	}
	
	public Contrada getByNome(String nome) {
		return contradaRepository.findByNome(nome);
	}
	public void save(Contrada contrada) {
		this.contradaRepository.save(contrada);
	}
	public void aggiungiContrada(String nome,String descrizione,String urlImmagine) {
		Contrada contrada=new Contrada();
		contrada.setNome(nome);
		contrada.setDescrizione(descrizione);
		contrada.setUrlImmagine(urlImmagine);
		this.save(contrada);
	}

		public void inizializza() {
			this.aggiungiContrada("Aquila", "Simbolo di potere e vittoria. Colori nero, giallo e blu.", "/aquila.jpg");
			this.aggiungiContrada("Bruco", "Rappresenta la rinascita e la trasformazione. Colori verde e giallo.", "/bruco.jpg");
			this.aggiungiContrada("Chiocciola", "Emblema di pazienza e tenacia. Colori rosso e giallo.", "/chiocciola.jpg");
			this.aggiungiContrada("Civetta", "Simbolo di saggezza e mistero. Colori nero e bianco.", "/civetta.jpg");
			this.aggiungiContrada("Drago", "Forza e fuoco, energia e lotta. Colori rosso e verde.", "/drago.jpg");
			this.aggiungiContrada("Giraffa", "Eleganza e fierezza. Colori bianco e rosso.", "/giraffa.jpg");
			this.aggiungiContrada("Istrice", "Difensiva e leale. Colori bianco, nero e rosso.", "/istrice.jpg");
			this.aggiungiContrada("Leocorno", "Simbolo magico e raro. Colori arancio e bianco.", "/leocorno.jpg");
			this.aggiungiContrada("Lupa", "Fiera e materna. Colori nero e bianco.", "/lupa.jpg");
			this.aggiungiContrada("Nicchio", "Simbolo marino, protettivo. Colori blu e giallo.", "/nicchio.jpg");
			this.aggiungiContrada("Oca", "Astuzia e leggerezza. Colori verde e bianco.", "/oca.jpg");
			this.aggiungiContrada("Onda", "Spirito marinaro, dinamismo. Colori bianco e celeste.", "/onda.jpg");
			this.aggiungiContrada("Pantera", "Eleganza e potenza felina. Colori rosso, blu e bianco.", "/pantera.jpg");
			this.aggiungiContrada("Selva", "Natura e forza selvaggia. Colori verde e arancio.", "/selva.jpg");
			this.aggiungiContrada("Tartuca", "Saggezza e protezione. Colori giallo e blu.", "/tartuca.jpg");
			this.aggiungiContrada("Torre", "Fierezza e indipendenza. Colori rosso e bianco.", "/torre.jpg");
			this.aggiungiContrada("Valdimontone", "Passione e determinazione. Colori rosso e giallo.", "/valdimontone.jpg");
		
	}
	
	
	
	
}
