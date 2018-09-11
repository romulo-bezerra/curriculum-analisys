package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Vaga;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;

@Named
@RequestScoped
@Data
public class InscricaoEmVagaController {
    
    private boolean empresaSelecionada;
    private Empresa empresa;
    
    @PostConstruct
    public void instanceObjects(){
        empresa = new Empresa();
    }

    @EJB
    private VagaService vagaService;

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }
    
    public void selecionarEmpresa(Empresa empresa){
        this.empresaSelecionada = true;
        this.empresa = empresa;
    }
    
    public List<Vaga> findAllVagas(){
        return vagaService.findAllVagas(empresa);
    }
    
}