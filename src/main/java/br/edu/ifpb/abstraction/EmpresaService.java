package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Empresa;
import java.util.List;

public interface EmpresaService {

    public void save(Empresa empresa);

    public boolean isRegistered(String email);
    
    public List<Enum> getOptionsTipoEmpresa();

}