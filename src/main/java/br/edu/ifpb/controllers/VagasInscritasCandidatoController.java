package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Vaga;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;

@Named
@SessionScoped
@Data
public class VagasInscritasCandidatoController implements Serializable {

    @EJB
    private VagaService vagaService;

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public List<Vaga> findAllContains(int idCandidato) {
        return vagaService.findAllContains(idCandidato);
    }

}
