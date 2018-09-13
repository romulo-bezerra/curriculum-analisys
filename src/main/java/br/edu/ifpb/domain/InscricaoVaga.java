package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class InscricaoVaga implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @OneToMany
    private List<Idioma> idiomas;
    @OneToMany
    private List<Habilidade> habilidades;
    @OneToMany
    private List<Atitude> atitudes;
    @OneToOne
    private Candidato candidato;

    private String objetivo;
    
    public InscricaoVaga() {
        this.idiomas = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.atitudes = new ArrayList<>();
    }
    
    public boolean addAtutude (Atitude atitude) {
        return atitudes.add(atitude);
    }
    
    public boolean addIdioma (Idioma idioma) {
        return idiomas.add(idioma);
    }
    
    public boolean addHabilidade (Habilidade habilidade) {
        return habilidades.add(habilidade);
    }
    
}