package br.edu.ifpb.domain.embeddable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco implements Serializable {
    
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String cidade;
    private String complemento;
    
}