package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.HabilidadeService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
public class HabilidadeController implements Serializable {

    private String[] habilidadesValues;
    private List<String> habilidadesItems;

    @EJB
    private HabilidadeService habilidadeService;

    @PostConstruct
    public void init() {
        habilidadesItems = findRandomAbiliity();
    }

    public List<String> findRandomAbiliity() {
        List<String> habilidadesString = new ArrayList<>();
        habilidadeService.findRandomAbiliity().forEach((habilidade) -> {
            habilidadesString.add(habilidade.getHabilidade());
        });
        return habilidadesString;
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public String mostrarListAbiliities() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(habilidadesValues));
        return null;
    }

}
