package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Atitude;
import java.util.List;

public interface AtitudeService {
    
    public void save(Atitude atitude);
    
    public void save(List<String> atitudes);
    
}