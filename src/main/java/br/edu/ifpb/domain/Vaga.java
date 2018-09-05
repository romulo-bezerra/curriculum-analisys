package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Vaga implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String titulo;
    @Lob
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String horasDiarias;
    @Column(nullable = false)
    private int numeroVagas;
    @Column(nullable = false)
    private double salario;
    @Column(nullable = false)
    private String prazoInscrição;
    @Lob
    @Column(nullable = false)
    private String missaoDaEmpresa;
    @Lob
    @Column(nullable = false)
    private String visaoDaEmpresa;
    
    @OneToMany
    private List<Idioma> idiomas;
    @OneToMany
    private List<Habilidade> habilidades;
    @OneToMany
    private List<Atitude> atitudes;
    
    @OneToMany
    private List<InscricaoVaga> inscricoesVagas;

    public Vaga() {
        this.idiomas = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.atitudes = new ArrayList<>();
        this.inscricoesVagas = new ArrayList<>();
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
    
    public boolean addInscricaoVaga (InscricaoVaga inscricaoVaga) {
        return inscricoesVagas.add(inscricaoVaga);
    }
    
}