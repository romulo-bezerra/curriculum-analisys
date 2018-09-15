package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.InscricaoVaga;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Data;

@Named
@RequestScoped
@Data
public class SelecionaInscricaoVagaController {

    private InscricaoVaga inscricaoVaga;

    @PostConstruct
    public void instanceObjects() {
        inscricaoVaga = new InscricaoVaga();
    }

    public String selecionarInscricaoVagaToView(InscricaoVaga inscricaoVaga) {
        this.inscricaoVaga = inscricaoVaga;
        return "view-inscricaovaga.xhtml";
    }

}
