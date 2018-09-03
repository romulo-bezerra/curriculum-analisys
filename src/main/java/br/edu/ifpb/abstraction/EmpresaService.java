package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Empresa;

public interface EmpresaService {

    public void save(Empresa empresa);

    public boolean isRegistered(String email);
    
}