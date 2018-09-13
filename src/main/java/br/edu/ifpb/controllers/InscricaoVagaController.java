package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.AtitudeService;
import br.edu.ifpb.abstractions.HabilidadeService;
import br.edu.ifpb.abstractions.IdiomaService;
import br.edu.ifpb.abstractions.InscricaoVagaService;
import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
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
public class InscricaoVagaController implements Serializable {

    private boolean vagaSelecionada;
    private Vaga vaga;
    private String[] atitudesValues;
    private List<String> atitudesItems;
    private String[] habilidadesValues;
    private List<String> habilidadesItems;
    private String[] idiomasValues;
    private List<String> idiomasItems;
    private InscricaoVaga inscricaoVaga;
    private Candidato candidato;

    @EJB
    private HabilidadeService habilidadeService;
    @EJB
    private AtitudeService atitudeService;
    @EJB
    private IdiomaService idiomaService;
    @EJB
    private VagaService vagaService;
    @EJB
    private InscricaoVagaService inscricaoVagaService;

    @PostConstruct
    public void instanceObjects() {
        vaga = new Vaga();
        inscricaoVaga = new InscricaoVaga();
        candidato = new Candidato();
    }

    public String selecionarVaga(Vaga vaga, Candidato candidato) {
        this.vagaSelecionada = true;
        this.vaga = vaga;
        this.candidato = candidato;

        atitudesItems = findRandomAttitudes();
        habilidadesItems = findRandomAbiliity();
        idiomasItems = findRandomLanguages();

        atitudesItems.addAll(findAllAttitudesVaga());
        habilidadesItems.addAll(findAllAbiliitiesVaga());
        idiomasItems.addAll(findAllLanguagesVaga());

        return "cadastro-em-vaga.xhtml";
    }

    public String save() {

        inscricaoVaga.setAtitudes(arrayStringToAtitudes());
        inscricaoVaga.setHabilidades(arrayStringToHabilidades());
        inscricaoVaga.setIdiomas(arrayStringToIdiomas());
        inscricaoVaga.setCandidato(candidato);

        inscricaoVagaService.save(inscricaoVaga);

        vaga.addInscricaoVaga(inscricaoVaga);
        vagaService.update(vaga);

        mensagemErro("Cadastro em Vaga", vaga.toString());

        return null;
    }

    public List<String> findRandomAttitudes() {
        List<String> atitudesString = new ArrayList<>();
        atitudeService.findRandomAttitudes().forEach((atitude) -> {
            atitudesString.add(atitude.getAtitude());
        });
        return atitudesString;
    }

    public List<String> findAllAttitudesVaga() {
        List<String> atitudesString = new ArrayList<>();
        vagaService.getAtitudes(vaga).forEach((atitude) -> {
            atitudesString.add(atitude.getAtitude());
        });
        return atitudesString;
    }

    public List<String> findAllAbiliitiesVaga() {
        List<String> habilidadesString = new ArrayList<>();
        vagaService.getHabilidades(vaga).forEach((habilidade) -> {
            habilidadesString.add(habilidade.getHabilidade());
        });
        return habilidadesString;
    }

    public List<String> findAllLanguagesVaga() {
        List<String> idiomasString = new ArrayList<>();
        vagaService.getIdioma(vaga).forEach((idioma) -> {
            idiomasString.add(idioma.getIdioma());
        });
        return idiomasString;
    }

    public List<String> findRandomAbiliity() {
        List<String> habilidadesString = new ArrayList<>();
        habilidadeService.findRandomAbiliity().forEach((habilidade) -> {
            habilidadesString.add(habilidade.getHabilidade());
        });
        return habilidadesString;
    }

    public List<String> findRandomLanguages() {
        List<String> idiomasString = new ArrayList<>();
        idiomaService.findRandomLanguages().forEach((idioma) -> {
            idiomasString.add(idioma.getIdioma());
        });
        return idiomasString;
    }

//    public String save(){
//        mensagemErro("Cadastro em Vaga", "Iidomas"+Arrays.toString(idiomasValues));
//        mensagemErro("Cadastro em Vaga", "Atitudes"+Arrays.toString(atitudesValues));
//        mensagemErro("Cadastro em Vaga", "Habilidades"+Arrays.toString(habilidadesValues));
//        return null;
//    }
    public String mostrarListIdiomas() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(idiomasValues));
        return null;
    }

    public String mostrarListAbiliities() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(habilidadesValues));
        return null;
    }

    public String mostrarListAtittudes() {
        mensagemErro("Cadastro em Vaga", Arrays.toString(atitudesValues));
        return null;
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public List<Atitude> arrayStringToAtitudes() {
        return atitudeService.findAllByText(atitudesValues);
    }

    public List<Idioma> arrayStringToIdiomas() {
        return idiomaService.findAllByText(idiomasValues);
    }

    public List<Habilidade> arrayStringToHabilidades() {
        return habilidadeService.findAllByText(habilidadesValues);
    }

}
