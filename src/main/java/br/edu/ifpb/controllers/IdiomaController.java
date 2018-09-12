package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.IdiomaService;
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
public class IdiomaController implements Serializable {

    private String[] idiomasValues;
    private List<String> idiomasItems;

    @EJB
    private IdiomaService idiomaService;
    
    @PostConstruct
    public void init(){
        idiomasItems = findRandomLanguages();
    }

    public List<String> findRandomLanguages() {
        List<String> idiomasString = new ArrayList<>();
        idiomaService.findRandomLanguages().forEach((idioma) -> {
            idiomasString.add(idioma.getIdioma());
        });
        return idiomasString;
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public String mostrarListIdiomas() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(idiomasValues));
        return null;
    }

}
