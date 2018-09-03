package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.Empresa;
import java.util.List;

public interface LoginService {

    public Candidato authenticateUser(String email, String senha);
    
    public Empresa authenticateCompany(String email, String senha);
    
    public List<String> getOptionsTipoLogin();
    
}