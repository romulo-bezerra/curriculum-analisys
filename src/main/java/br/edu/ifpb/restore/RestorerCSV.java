package br.edu.ifpb.restore;

import br.edu.ifpb.abstractions.AtitudeService;
import br.edu.ifpb.abstractions.HabilidadeService;
import br.edu.ifpb.abstractions.IdiomaService;
import br.edu.ifpb.util.ReaderCSV;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class RestorerCSV {

    @Inject
    private ReaderCSV readCSV;
    @Inject
    private IdiomaService idiomaService;
    @Inject
    private HabilidadeService habilidadeService;
    @Inject
    private AtitudeService atitudeService;

    public RestorerCSV() {
    }

    public void restoreIdiomasCSV() {
        //Temp: Trocar por caminho relativo
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/curriculum-analisys/src/main/resources/csvs/idiomas.csv");
        idiomaService.save(lista);
    }

    public void restoreHabilidadesCSV() {
        //Temp: Trocar por caminho relativo
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/curriculum-analisys/src/main/resources/csvs/habilidades-vaga-programador.csv");
        habilidadeService.save(lista);
    }

    public void restoreAtitudesCSV() {
        //Temp: Trocar por caminho relativo
        List<String> lista = readCSV.readCSV("/home/romulo/Área de Trabalho/curriculum-analisys/curriculum-analisys/src/main/resources/csvs/atitudes.csv");
        atitudeService.save(lista);
    }

}
