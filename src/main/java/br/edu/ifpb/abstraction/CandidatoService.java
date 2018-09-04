package br.edu.ifpb.abstraction;

import br.edu.ifpb.domain.Candidato;

public interface CandidatoService {

    public void save(Candidato candidato);

    public boolean isRegistered(String email);
    
}