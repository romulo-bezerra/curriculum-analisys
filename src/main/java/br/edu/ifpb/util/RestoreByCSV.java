package br.edu.ifpb.util;

import br.edu.ifpb.abstraction.AtitudeService;
import br.edu.ifpb.abstraction.HabilidadeService;
import br.edu.ifpb.abstraction.IdiomaService;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class RestoreByCSV {

    @Inject
    private ReadCSV readCSV;
    @Inject
    private IdiomaService idiomaService;
    @Inject
    private HabilidadeService habilidadeService;
    @Inject
    private AtitudeService atitudeService;

    public RestoreByCSV() {
    }

    public void restoreIdiomasCSV() {
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/src/main/resources/csvs/idiomas.csv");
        idiomaService.save(lista);
    }

    public void restoreHabilidadesCSV() {
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/src/main/resources/csvs/habilidades-vaga-programador.csv");
        habilidadeService.save(lista);
    }

    public void restoreAtitudesCSV() {
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/src/main/resources/csvs/atitudes.csv");
        atitudeService.save(lista);
    }

}
