package br.edu.ifpb.domain;

import br.edu.ifpb.enums.Origem;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Idioma implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String idioma;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Origem origem;
    
    public Idioma(String idioma, Origem origem) {
        this.idioma = idioma;
        this.origem = origem;
    }
}