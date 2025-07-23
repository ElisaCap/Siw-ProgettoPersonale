package snippet;

public class Snippet {
	
	public void aggiungiContrada(String nome, String descrizione, String descrizioneLunga, String urlImmagine) {
		Contrada contrada = new Contrada();
		contrada.setNome(nome);
		contrada.setDescrizione(descrizione);
		contrada.setDescrizioneLunga(descrizioneLunga);
		try {
	        ClassPathResource imgFile = new ClassPathResource("static" + urlImmagine);
	        try (InputStream in = imgFile.getInputStream()) {
	            contrada.setUrlImmagine(in.readAllBytes());
	        }
	    } catch (IOException e) {
	        // puoi loggare o gestire diversamente
	        contrada.setUrlImmagine(null); // o un'immagine di default
	        System.err.println("Errore nel caricamento immagine per " + nome + ": " + e.getMessage());
	    }
		this.save(contrada);
	}
	
	public void inizializza() {
		this.aggiungiContrada("Aquila", "Simbolo di potere e vittoria.",
				"L'Aquila è una delle contrade più antiche di Siena, situata nel Terzo di Camollia. Il suo simbolo, l'aquila imperiale nera, riflette l'influenza dell'Impero su Siena nel Medioevo. Tradizionalmente associata alla nobiltà e all'autorità, l'Aquila si distingue per il suo passato glorioso, legato anche alla costruzione del Duomo di Siena. Le sue vittorie al Palio sono numerose e i suoi colori — nero, giallo e blu — riflettono regalità e potenza.",
				"/immaginisiw/contrade/aquila.png");
}

