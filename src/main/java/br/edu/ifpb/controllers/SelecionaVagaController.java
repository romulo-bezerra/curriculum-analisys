package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.Vaga;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

@Named
@SessionScoped
@Data
public class SelecionaVagaController implements Serializable {

    private boolean vagaSelecionada;
    private Vaga vaga;
    
    @PostConstruct
    public void instanceObjects() {
        vaga = new Vaga();
    }

    public String selecionarVaga(Vaga vaga) {
        this.vagaSelecionada = true;
        this.vaga = vaga;
        return "cadastro-em-vaga.xhtml";
    }

}