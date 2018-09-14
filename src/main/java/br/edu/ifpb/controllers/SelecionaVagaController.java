package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.Vaga;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Data;

@Named
@RequestScoped
@Data
public class SelecionaVagaController {

    private Vaga vaga;

    @PostConstruct
    public void instanceObjects() {
        vaga = new Vaga();
    }

    public String selecionarVagaToView(Vaga vaga) {
        this.vaga = vaga;
        return "view-vaga.xhtml";
    }


}
