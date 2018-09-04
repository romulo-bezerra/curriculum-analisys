package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.Empresa;

public interface LoginService {

    public Candidato authenticateUser(String email, String senha);
    
    public Empresa authenticateCompany(String email, String senha);
    
}