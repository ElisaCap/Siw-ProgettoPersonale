package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
public class Cavallo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "campo obbligatorio")
    private String nome;
    @NotBlank(message = "campo obbligatorio")
    private String razza;

    
    @org.springframework.data.annotation.Transient
    @Transient // non salvare nel DB
    private MultipartFile fileImmagine;

    // getter e setter per fileImmagine
    public MultipartFile getFileImmagine() {
        return fileImmagine;
    }

    public void setFileImmagine(MultipartFile fileImmagine) {
        this.fileImmagine = fileImmagine;
    }

    
    @Lob
    private byte[] immagine; // campo per l’immagine

    @OneToMany(mappedBy = "cavallo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Partecipazione> partecipazioni;

    // getter e setter

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
    public String getRazza() {
        return razza;
    }
    public void setRazza(String razza) {
        this.razza = razza;
    }
    public byte[] getImmagine() {
        return immagine;
    }
    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
    }
    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }
    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, razza);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cavallo other = (Cavallo) obj;
        return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(razza, other.razza);
    }
}
