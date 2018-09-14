package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Vaga;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

@Named
@SessionScoped
@Data
public class SelecionaEmpresaController implements Serializable {

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
    
    public String selecionarEmpresaToView(Empresa empresa) {
        this.empresa = empresa;
        return "view-empresa.xhtml";
    }
    
    public List<Vaga> findAllVagas() {
        return vagaService.findAllVagas(empresa);
    }

}
