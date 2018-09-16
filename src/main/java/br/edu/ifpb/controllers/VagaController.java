package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.EmpresaService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;
import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.Vaga;
import br.edu.ifpb.enums.Estado;
import br.edu.ifpb.enums.Origem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
@Data
public class VagaController implements Serializable {

    private Vaga vaga;
    private String atitude;
    private String habilidade;
    private String idioma;
    private String atitudeDell;
    private String habilidadeDell;
    private String idiomaDell;
    private boolean atitudeAdded;
    private boolean habilidadeAdded;
    private boolean idiomaAdded;
    private String statusDeInsercaoAtitude;
    private String statusDeInsercaoHabilidade;
    private String statusDeInsercaoIdioma;
    private List<Atitude> atitudesVaga;
    private List<Habilidade> habilidadesVaga;
    private List<Idioma> idiomasVaga;

    @PostConstruct
    public void initObjects() {
        vaga = new Vaga();
        atitudesVaga = new ArrayList<>();
        habilidadesVaga = new ArrayList<>();
        idiomasVaga = new ArrayList<>();
    }

    @EJB
    private EmpresaService empresaService;
    @EJB
    private VagaService vagaService;

    public String addVaga(Empresa empresa) {
        vaga.setIdiomas(idiomasVaga);
        vaga.setHabilidades(habilidadesVaga);
        vaga.setAtitudes(atitudesVaga);
        vaga.setEstado(Estado.ABERTA);

        empresa.addVaga(vaga);
        empresaService.update(empresa);

        vaga = new Vaga();

        //Temp
        mensagemErro("Cadastro Vaga", "VAGA CADASTRADA!");
        return "vagas-empresa.xhtml";
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public List<Vaga> findAllVagas(Empresa empresa) {
        return vagaService.findAllVagas(empresa);
    }

    public String addAtitude() {
        statusDeInsercaoAtitude = "";
        if (vagaService.containInvalidCharacter(atitude)
                || vagaService.containNumber(atitude)) {
            mensagemErro("Cadastro Vaga", "O campo atitudes contêm caracteres ilegais");
            return null;
        } else if (vagaService.isEmpty(atitude)) {
            mensagemErro("Cadastro Vaga", "O campo atitudes não pode ficar em branco");
            return null;
        }
        Atitude a = new Atitude(atitude, Origem.ORIGINARIO_DO_CANDIDATO);
        if (atitudesVaga.add(a)) {
            atitudeAdded = true;
            statusDeInsercaoAtitude = "Atitude: \"" + atitude + "\"" + " inserida com sucesso!";
            atitudeDell = atitude;
            atitude = "";
        }
        return null;
    }

    public String addHabilidade() {
        statusDeInsercaoHabilidade = "";
        if (vagaService.containInvalidCharacter(habilidade)
                || vagaService.containNumber(habilidade)) {
            mensagemErro("Cadastro Vaga", "O campo habilidades contêm caracteres ilegais");
            return null;
        } else if (vagaService.isEmpty(habilidade)) {
            mensagemErro("Cadastro Vaga", "O campo habilidades não pode ficar em branco");
            return null;
        }
        Habilidade h = new Habilidade(habilidade, Origem.ORIGINARIO_DO_CANDIDATO);
        if (habilidadesVaga.add(h)) {
            habilidadeAdded = true;
            statusDeInsercaoHabilidade = "Habilidade: \"" + habilidade + "\"" + " inserida com sucesso!";
            habilidadeDell = habilidade;
            habilidade = "";
        }
        return null;
    }

    public String addIdioma() {
        statusDeInsercaoIdioma = "";
        if (vagaService.containInvalidCharacter(idioma)
                || vagaService.containNumber(idioma)) {
            mensagemErro("Cadastro Vaga", "O campo idiomas contêm caracteres ilegais");
            return null;
        } else if (vagaService.isEmpty(idioma)) {
            mensagemErro("Cadastro Vaga", "O campo idiomas não pode ficar em branco");
            return null;
        }
        Idioma i = new Idioma(idioma, Origem.ORIGINARIO_DO_CANDIDATO);
        if (idiomasVaga.add(i)) {
            idiomaAdded = true;
            statusDeInsercaoIdioma = "Idioma: \"" + idioma + "\"" + " inserido com sucesso!";
            idiomaDell = idioma;
            idioma = "";
        }
        return null;
    }

    public String removeLatestIdioma() {
        if (idiomasVaga.isEmpty()) {
            mensagemErro("Cadastro Vaga", "A lista de idiomas já está vazia");
        } else {
            idiomasVaga.remove(idiomasVaga.size() - 1);
            statusDeInsercaoIdioma = "Idioma: \"" + idiomaDell + "\"" + " removido com sucesso!";
        }
        return null;
    }

    public String removeLatestAtitude() {
        if (atitudesVaga.isEmpty()) {
            mensagemErro("Cadastro Vaga", "A lista de atitudes já está vazia");
        } else {
            atitudesVaga.remove(atitudesVaga.size() - 1);
            statusDeInsercaoAtitude = "Atitude: \"" + atitudeDell + "\"" + " removido com sucesso!";
        }
        return null;
    }

    public String removeLatestHabilidade() {
        if (habilidadesVaga.isEmpty()) {
            mensagemErro("Cadastro Vaga", "A lista de habilidades já está vazia");
        } else {
            habilidadesVaga.remove(habilidadesVaga.size() - 1);
            statusDeInsercaoHabilidade = "Habilidade: \"" + habilidadeDell + "\"" + " removido com sucesso!";
        }
        return null;
    }

}
