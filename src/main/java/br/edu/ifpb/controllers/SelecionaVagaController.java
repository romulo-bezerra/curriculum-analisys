package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

@Named
@SessionScoped
@Data
public class SelecionaVagaController implements Serializable {

    private Vaga vaga;
    private InscricaoVaga inscricaoVaga;
    private BarChartModel barra;

    @PostConstruct
    public void instanceObjects() {
        vaga = new Vaga();
        inscricaoVaga = new InscricaoVaga();
    }

    public String selecionarVagaToView(Vaga vaga) {
        this.vaga = vaga;
        return "view-vaga.xhtml";
    }

    public String selecionarInscricaoVagaToView(InscricaoVaga inscricaoVaga) {
        this.inscricaoVaga = inscricaoVaga;
        return "view-inscricaovaga.xhtml";
    }

    public String carregarGrafico(Vaga vaga, InscricaoVaga inscricaoVaga) {
        this.inscricaoVaga = inscricaoVaga;
        this.vaga = vaga;
        showGraph();
        return "analitica-candidato.xhtml";
    }

    public void showGraph() {
        barra = new BarChartModel();

        ChartSeries serie = new BarChartSeries();

        serie.setLabel("Valores");

        serie.set("Habilidades", getPercentualCorrespondenciaHabilidades());
        serie.set("Atitudes", getPercentualCorrespondenciaAtitudes());
        serie.set("Idiomas", getPercentualCorrespondenciaIdiomas());

        barra.addSeries(serie);

        barra.setTitle("Avaliação do Candidato");
        barra.setLegendPosition("ne");
        barra.setAnimate(true);

        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Valores");

        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Pontuação");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

    public int getPercentualCorrespondenciaHabilidades() {

        int qntHabilidadesVaga = vaga.getHabilidades().size();

        int qntCorrespondencias = 0;

        for (Habilidade hv : vaga.getHabilidades()) {
            for (Habilidade hi : inscricaoVaga.getHabilidades()) {
                if (hv.getHabilidade().equalsIgnoreCase(hi.getHabilidade())) {
                    qntCorrespondencias++;
                    break;
                }
            }
        }
        if (qntCorrespondencias == 0 || qntHabilidadesVaga == 0) {
            return 0;
        }
        return (qntCorrespondencias * 100) / qntHabilidadesVaga;
    }

    public int getPercentualCorrespondenciaAtitudes() {

        int qntAtitudesVaga = vaga.getAtitudes().size();

        int qntCorrespondencias = 0;

        for (Atitude av : vaga.getAtitudes()) {
            for (Atitude ai : inscricaoVaga.getAtitudes()) {
                if (av.getAtitude().equalsIgnoreCase(ai.getAtitude())) {
                    qntCorrespondencias++;
                    break;
                }
            }
        }
        if (qntCorrespondencias == 0 || qntAtitudesVaga == 0) {
            return 0;
        }
        return (qntCorrespondencias * 100) / qntAtitudesVaga;
    }

    public int getPercentualCorrespondenciaIdiomas() {

        int qntIdiomasVaga = vaga.getIdiomas().size();

        int qntCorrespondencias = 0;

        for (Idioma iv : vaga.getIdiomas()) {
            for (Idioma ii : inscricaoVaga.getIdiomas()) {
                if (iv.getIdioma().equalsIgnoreCase(ii.getIdioma())) {
                    qntCorrespondencias++;
                    break;
                }
            }
        }
        if (qntCorrespondencias == 0 || qntIdiomasVaga == 0) {
            return 0;
        }
        return (qntCorrespondencias * 100) / qntIdiomasVaga;
    }

}
