package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.EmpresaService;
import br.edu.ifpb.domain.Empresa;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;

@Named
@RequestScoped
@Data
public class BuscaEmpresaController {
    
    private String infoRelated;
    private boolean pesquisado;

    @EJB
    private EmpresaService empresaService;

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }
    
    public List<Empresa> findAllByAttributes(){
        pesquisado = true;
        return empresaService.findAllByAttributes(infoRelated);
    }
    
    public List<Empresa> findAll(){
        return empresaService.findAll();
    }
    
}