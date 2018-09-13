package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Idioma;
import java.util.List;

public interface IdiomaService {
    
    public void save(Idioma idioma);
    
    public void save(List<String> idiomas);
    
    public List<Idioma> findRandomLanguages();
    
    public List<Idioma> findAllByText(String[] array);
    
}