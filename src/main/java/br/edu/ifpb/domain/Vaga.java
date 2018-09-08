package br.edu.ifpb.domain;

import br.edu.ifpb.converters.LocalDateConverter;
import br.edu.ifpb.enums.Estado;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataMaxInscricao;
    @Lob
    @Column(nullable = false)
    private String missaoDaEmpresa;
    @Lob
    @Column(nullable = false)
    private String visaoDaEmpresa;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
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