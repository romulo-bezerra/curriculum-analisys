package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.InscricaoVagaService;
import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;

@Named
@SessionScoped
@Data
public class VagasInscritasEmpresaController implements Serializable {

    private boolean vagaSelecionada;
    private Vaga vaga;
    
    @PostConstruct
    public void init(){
        vaga = new Vaga();
    }
    
    @EJB
    private InscricaoVagaService inscricaoVagaService;
    @EJB
    private VagaService vagaService;

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public List<Vaga> findAllWithInscricao(Empresa empresa){
        return vagaService.findAllWithInscricao(empresa);
    }
    
    public void selecionarVaga(Vaga vaga){
        vagaSelecionada = true;
        this.vaga = vaga;
    }
    
    public List<InscricaoVaga> findAllInscricoesVaga(){
        return inscricaoVagaService.findAll(vaga);
    }

}