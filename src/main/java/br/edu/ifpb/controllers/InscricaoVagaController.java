package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Vaga;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Data;

@Named
@RequestScoped
@Data
//Tem q mudar tudo; classe copiada
public class InscricaoVagaController {

    private boolean empresaSelecionada;
    private Empresa empresa;

    @EJB
    private VagaService vagaService;

    @PostConstruct
    public void instanceObjects() {
        empresa = new Empresa();
    }

    public void selecionarEmpresa(Empresa empresa) {
        this.empresaSelecionada = true;
        this.empresa = empresa;
    }

    public List<Vaga> findAllVagas() {
        return vagaService.findAllVagas(empresa);
    }

}
