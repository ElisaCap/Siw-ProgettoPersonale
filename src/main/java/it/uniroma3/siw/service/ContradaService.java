package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Contrada;
import it.uniroma3.siw.repository.ContradaRepository;

@Service
public class ContradaService {
	@Autowired
	private ContradaRepository contradaRepository;

	public Iterable<Contrada> getAll() {
		return contradaRepository.findAll();
	}

	public Contrada getByNome(String nome) {
		return contradaRepository.findByNome(nome);
	}

	public void save(Contrada contrada) {
		this.contradaRepository.save(contrada);
	}

	public void aggiungiContrada(String nome, String descrizione, String descrizioneLunga, String urlImmagine) {
		Contrada contrada = new Contrada();
		contrada.setNome(nome);
		contrada.setDescrizione(descrizione);
		contrada.setDescrizioneLunga(descrizioneLunga);
		contrada.setUrlImmagine(urlImmagine);
		this.save(contrada);
	}

	public void inizializza() {
		this.aggiungiContrada("Aquila", "Simbolo di potere e vittoria.",
				"L'Aquila è una delle contrade più antiche di Siena, situata nel Terzo di Camollia. Il suo simbolo, l'aquila imperiale nera, riflette l'influenza dell'Impero su Siena nel Medioevo. Tradizionalmente associata alla nobiltà e all'autorità, l'Aquila si distingue per il suo passato glorioso, legato anche alla costruzione del Duomo di Siena. Le sue vittorie al Palio sono numerose e i suoi colori — nero, giallo e blu — riflettono regalità e potenza.",
				"/aquila.png");

		this.aggiungiContrada("Bruco", "Rappresenta la rinascita e la trasformazione.",
				"Il Bruco si trova nel Terzo di Camollia ed è una contrada popolare, storicamente abitata da artigiani della seta. Il bruco, che si trasforma in farfalla, è simbolo di cambiamento, speranza e crescita. Ha un forte spirito comunitario ed è conosciuta per l’intensità delle sue celebrazioni e la passione per il Palio. I colori verde e giallo riflettono la vitalità e l’energia del rione.",
				"/bruco.png");

		this.aggiungiContrada("Chiocciola", "Emblema di pazienza e tenacia.",
				"La Chiocciola è situata nel Terzo di San Marco, nel rione di San Giovanni. Il suo animale totemico, la chiocciola, rappresenta la costanza e la determinazione. Nonostante la sua lentezza simbolica, la Chiocciola è tra le contrade più competitive del Palio. I suoi colori, rosso e giallo, riflettono la passione e la resilienza dei suoi contradaioli. La contrada ha origini medievali e una lunga storia di rivalità, soprattutto con la Tartuca.",
				"/chiocciola.png");

		this.aggiungiContrada("Civetta", "Simbolo di saggezza e mistero.",
				"La Civetta si trova nel Terzo di San Marco, ed è una contrada intellettuale e raffinata, spesso associata agli artigiani e agli studiosi. Il suo simbolo, la civetta, richiama la conoscenza e il silenzio della notte. È una delle contrade più amate per il suo spirito indipendente. I suoi colori, nero e bianco, indicano il dualismo tra luce e ombra, mente e corpo, tradizione e modernità.",
				"/civetta.png");

		this.aggiungiContrada("Drago", "Forza e fuoco, energia e lotta.",
				"Il Drago, situato nel cuore del Terzo di Camporegio, è una contrada dal forte spirito combattivo. Il drago, creatura mitologica di fuoco e potenza, simboleggia la lotta continua e l'ardore con cui i contradaioli affrontano la vita e il Palio. Storicamente ha avuto legami con la nobiltà senese e ha mantenuto un'immagine fieramente indipendente. I colori rosso e verde sono il segno della sua vitalità e passione.",
				"/drago.png");
		this.aggiungiContrada("Giraffa", "Eleganza e fierezza.",
				"La Giraffa è situata nel Terzo di Camollia. Il suo animale, proveniente dall’immaginario esotico dell’Africa, rappresenta la grazia, l’eleganza e la visione dall’alto. È tra le contrade più vincenti del Palio e molto amata per il suo spirito fiero e nobile. Storicamente, i suoi abitanti erano commercianti e notabili. I colori bianco e rosso evocano purezza e passione.",
				"/giraffa.png");

		this.aggiungiContrada("Istrice", "Difensiva e leale.",
				"L’Istrice si trova nel Terzo di Camollia, ed è una contrada fiera, nota per la sua tenacia e la capacità di difendere il proprio territorio e la propria comunità. Il simbolo dell’istrice, animale protetto da aculei, richiama la prudenza ma anche l’aggressività quando minacciato. Anticamente frequentata da artigiani e muratori, la contrada è orgogliosa del suo spirito battagliero. I suoi colori — bianco, nero e rosso — sottolineano forza, equilibrio e passione.",
				"/istrice.png");

		this.aggiungiContrada("Leocorno", "Simbolo magico e raro.",
				"Il Leocorno, creatura mitologica simile a un unicorno, rappresenta purezza, magia e unicità. La contrada si trova nel Terzo di San Martino. Le sue origini sono medievali e il suo carattere distintivo è sempre stato quello di ambizione e indipendenza. I suoi colori arancio e bianco rispecchiano l’energia del sole e la nobiltà dello spirito. La sua storia è fatta di sfide vinte con perseveranza e cuore.",
				"/leocorno.png");

		this.aggiungiContrada("Lupa", "Fiera e materna.",
				"La Lupa ha una forte connessione simbolica con la leggendaria fondazione di Roma e quindi con la storia classica. È situata nel Terzo di Camollia ed è nota per la sua orgogliosa identità. Il suo animale rappresenta forza protettiva, maternità e resistenza. I colori nero e bianco riflettono la forza d’animo e la dignità. Storicamente associata agli intellettuali e ai librai di Siena, è una contrada che vanta una storia intensa e combattiva.",
				"/lupa.png");

		this.aggiungiContrada("Nicchio", "Simbolo marino, protettivo.",
				"Il Nicchio, con sede nel Terzo di Castelmontorio, ha come simbolo la conchiglia, emblema di protezione, pellegrinaggio e legame con il mare. La contrada ha origini marinare e richiama nei suoi colori blu e giallo la profondità dell’acqua e la luce del sole. Famosa per la sua ospitalità e il forte senso di appartenenza, il Nicchio ha avuto storiche rivalità con Montone e Torre.",
				"/nicchio.png");

		this.aggiungiContrada("Oca", "Astuzia e leggerezza.",
				"L’Oca è una delle contrade più vittoriose del Palio, ed è situata nel Terzo di Castelmontorio. Simboleggia l’intelligenza, la mobilità e lo spirito libero. Il suo simbolo animale è legato anche alla leggenda di Roma e dei suoi salvataggi. Storicamente popolata da lavandaie e popolani, è una contrada che ha saputo imporsi con carattere e intelligenza. I suoi colori verde e bianco richiamano natura e purezza.",
				"/oca.png");

		this.aggiungiContrada("Onda", "Spirito marinaro, dinamismo.",
				"L’Onda si trova nel Terzo di San Martino e ha come simbolo una delfina coronata che richiama la potenza del mare e la nobiltà d’animo. È l’unica contrada a vantare un legame diretto con la Repubblica Marinara di Pisa, nonostante Siena sia una città dell'entroterra. Il suo dinamismo si riflette nei suoi colori: bianco e celeste. È una contrada famosa per la sua vitalità, il carattere socievole e lo spirito battagliero.",
				"/onda.png");

		this.aggiungiContrada("Pantera", "Eleganza e potenza felina.",
				"La Pantera si trova nel Terzo di San Martino. Il suo animale simboleggia eleganza, agilità e una forza silenziosa. Anticamente, i suoi abitanti erano musicisti e artisti, il che ha influenzato il suo spirito creativo. I colori rosso, blu e bianco rappresentano il fuoco della passione, la profondità del pensiero e la chiarezza dell’intento. La Pantera è conosciuta per la sua raffinatezza e capacità strategica.",
				"/pantera.png");

		this.aggiungiContrada("Selva", "Natura e forza selvaggia.",
				"La Selva ha come simbolo una rinoceronte guidata da una quercia: insieme simboleggiano potenza, resistenza e radicamento. Situata nel Terzo di Camollia, la contrada ha storicamente avuto un legame con i boscaioli e i lavoratori della natura. I colori verde e arancio evocano il bosco e la vitalità della terra. La Selva è amata per il suo spirito libero e la sua tradizione legata alla natura incontaminata.",
				"/selva.png");

		this.aggiungiContrada("Tartuca", "Saggezza e protezione.",
				"La Tartuca si trova nel Terzo di San Marco, ed è rappresentata dalla tartaruga, simbolo di longevità, saggezza e protezione. I suoi abitanti storici erano spesso studiosi e religiosi. I colori giallo e blu riflettono la luce della conoscenza e la profondità dell’intelletto. La contrada è celebre per la sua compostezza e l’alto senso civico, con una rivalità molto sentita con la Chiocciola.",
				"/tartuca.png");

		this.aggiungiContrada("Torre", "Fierezza e indipendenza.",
				"La Torre è tra le contrade più fiere e combattive, situata nel Terzo di San Martino. Il suo simbolo, una torre rossa su campo bianco, rappresenta la difesa e la fermezza. Storicamente legata ai militari e agli artigiani del ferro, la contrada è nota per il suo spirito indipendente e le rivalità accesissime, soprattutto con Oca e Onda. I colori riflettono passione, determinazione e chiarezza d’intenti.",
				"/torre.png");

		this.aggiungiContrada("Valdimontone", "Passione e determinazione.",
				"Valdimontone, comunemente detta ‘Montone’, ha come simbolo un ariete in corsa, segno di forza frontale e di coraggio. La contrada, situata nel Terzo di San Martino, è celebre per l’ardore con cui affronta ogni Palio. Ha un’origine molto antica e le sue vittorie sono celebrate con grande entusiasmo. I colori rosso e giallo riflettono il fuoco interiore e la luminosità dell’ambizione.",
				"/valdimontone.png");

		// Per le altre contrade, posso proseguire con lo stesso schema.
		// Vuoi che continui per tutte le restanti?

	}

	public Contrada getByid(Long id) {
		return this.contradaRepository.findById(id).orElse(null);
	}

}
