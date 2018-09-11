package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Empresa;
import java.util.List;

public interface EmpresaService {

    public void save(Empresa empresa);

    public boolean isRegistered(String email);
    
    public void update(Empresa novoEstado);
    
    public List<Empresa> findAllByAttributes(String infoRelated);
    
    public List<Empresa> findAll();
    
}