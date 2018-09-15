package br.edu.ifpb.abstractions;

import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
import java.util.List;

public interface InscricaoVagaService {

    public void save(InscricaoVaga inscricaoVaga);

    public List<InscricaoVaga> findAll(Vaga vaga);

}
