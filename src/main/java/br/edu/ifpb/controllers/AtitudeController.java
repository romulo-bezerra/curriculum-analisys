package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.AtitudeService;
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
public class AtitudeController implements Serializable {

    private String[] atitudesValues;
    private List<String> atitudesItems;

    @EJB
    private AtitudeService atitudeService;
    
    @PostConstruct
    public void init(){
        atitudesItems = findRandomAttitudes();
    }

    public List<String> findRandomAttitudes() {
        List<String> atitudesString = new ArrayList<>();
        atitudeService.findRandomAttitudes().forEach((atitude) -> {
            atitudesString.add(atitude.getAtitude());
        });
        return atitudesString;
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public String mostrarListAtittudes() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(atitudesValues));
        return null;
    }

}
