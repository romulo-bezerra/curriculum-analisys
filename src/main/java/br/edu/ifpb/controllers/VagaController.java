package br.edu.ifpb.controllers;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;
import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.Vaga;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;

@Named
@RequestScoped
@Data
public class VagaController {

    private Vaga vaga;
    private String idiomasLongString;
    private String habilidadesLongString;
    private String atitudesLongString;

    @PostConstruct
    public void initObjects() {
        vaga = new Vaga();
    }

    @EJB
    private VagaService vagaService;

    public String save() {
        if (vagaService.containInvalidCharacter(idiomasLongString)
                || vagaService.containNumber(idiomasLongString)) {
            mensagemErro("Cadastro Vaga", "O campo idiomas contêm caracteres ilegais");
            return null;
        } else if (vagaService.containInvalidCharacter(habilidadesLongString)
                || vagaService.containNumber(habilidadesLongString)) {
            mensagemErro("Cadastro Vaga", "O campo habilidades contêm caracteres ilegais");
            return null;
        } else if (vagaService.containInvalidCharacter(atitudesLongString)
                || vagaService.containNumber(atitudesLongString)) {
            mensagemErro("Cadastro Vaga", "O campo atitudes contêm caracteres ilegais");
            return null;
        } else if(vaga.getDataMaxInscricao().isBefore(LocalDate.now()) 
                || vaga.getDataMaxInscricao().isEqual(LocalDate.now())){
            mensagemErro("Cadastro Vaga", "A data não pode ser passada ou para o mesmo dia");
            return null;
        }
        else {
            List<Idioma> idiomas = vagaService.extractIdiomas(this.idiomasLongString);
            List<Habilidade> habilidades = vagaService.extractHabilidades(this.habilidadesLongString);
            List<Atitude> atitudes = vagaService.extractAtitudes(this.atitudesLongString);

            vaga.setIdiomas(idiomas);
            vaga.setHabilidades(habilidades);
            vaga.setAtitudes(atitudes);

            vagaService.save(vaga);
            vaga = new Vaga();
            mensagemErro("Cadastro Vaga", "VAGA CADASTRADA!");
            return null;
        }

    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

}
