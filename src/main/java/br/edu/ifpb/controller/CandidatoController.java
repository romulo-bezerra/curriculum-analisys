package br.edu.ifpb.controller;

import br.edu.ifpb.abstraction.CandidatoService;
import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.embeddables.Endereco;
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
public class CandidatoController {

    private Candidato candidato;
    private Endereco endereco;

    @EJB
    private CandidatoService candidatoService;

    @PostConstruct
    public void initObjects() {
        this.candidato = new Candidato();
        this.endereco = new Endereco();
    }

    public String save() {
        if (candidatoService.isRegistered(candidato.getEmail())) {
            mensagemErro("Cadastro Candidato", "Já existe um candidato "
                    + "cadastrado com o e-mail informado!");
            return null;
        } else {
            String sexoUpperCase = candidato.getSexo().toUpperCase();
            candidato.setSexo(sexoUpperCase);
            candidato.setEndereco(endereco);
            candidatoService.save(candidato);
            return "faces/login.xhtml";
        }
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public List<String> optionsSexo() {
        return candidatoService.getOptionsSexo();
    }
}
