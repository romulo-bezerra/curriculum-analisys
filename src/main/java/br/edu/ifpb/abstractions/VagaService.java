package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.Vaga;
import java.util.List;

public interface VagaService {

    public void save(Vaga vaga);

    public List<Idioma> extractIdiomas(String text);

    public List<Habilidade> extractHabilidades(String text);

    public List<Atitude> extractAtitudes(String text);
    
    public boolean containNumber(String text);
    
    public boolean containInvalidCharacter(String text);
    
    public List<Vaga> findAllVagas(Empresa empresa);

}