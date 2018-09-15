package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.InscricaoVagaService;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InscricaoVagaServiceImpl implements InscricaoVagaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    @Override
    public void save(InscricaoVaga isncricaoVaga) {
        entityManager.persist(isncricaoVaga);
    }

    @Override
    public List<InscricaoVaga> findAll(Vaga vaga) {
        return vaga.getInscricoesVagas();
    }

}
