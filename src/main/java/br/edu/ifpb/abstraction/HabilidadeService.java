package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Habilidade;
import java.util.List;

public interface HabilidadeService {
    
    public void save(Habilidade habilidade);
    
    public void save(List<String> habilidades);
    
}