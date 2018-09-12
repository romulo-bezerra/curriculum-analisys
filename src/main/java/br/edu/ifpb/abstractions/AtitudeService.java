package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Atitude;
import java.util.List;

public interface AtitudeService {
    
    public void save(Atitude atitude);
    
    public void save(List<String> atitudes);
    
    public List<Atitude> findRandomAttitudes();
    
    public Atitude findById(int id);
    
}