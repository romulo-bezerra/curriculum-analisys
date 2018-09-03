package br.edu.ifpb.controller;

import br.edu.ifpb.abstraction.CandidatoService;
import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.embeddables.Endereco;
import br.edu.ifpb.enums.Sexo;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
            candidato.setEndereco(endereco);
            candidatoService.save(candidato);
            return "login.xhtml";
        }
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public SelectItem[] optionsSexo() {
        SelectItem[] items = new SelectItem[Sexo.values().length];
        int i = 0;
        for (Sexo s : Sexo.values()) {
            items[i++] = new SelectItem(s, s.getLabel());
        }
        return items;
    }
}
