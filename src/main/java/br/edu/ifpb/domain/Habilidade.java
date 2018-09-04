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
public class Habilidade implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String habilidade;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Origem origem;
    
    public Habilidade(String habilidade, Origem origem) {
        this.habilidade = habilidade;
        this.origem = origem;
    }
    
}