package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Candidato;
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
    
    public void update(Vaga novoEstado);
    
    public List<Atitude> getAtitudes(Vaga vaga);
    
    public List<Habilidade> getHabilidades(Vaga vaga);
    
    public List<Idioma> getIdioma(Vaga vaga);
    
    public List<Vaga> findAllWithInscricao(Empresa empresa);
    
    public List<Vaga> findAllContains(int idCandidato);
    
}