package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Candidato;
import java.util.List;

public interface CandidatoService {

    public void save(Candidato candidato);

    public boolean isRegistered(String email);
    
    public List<String> getOptionsSexo();

}
