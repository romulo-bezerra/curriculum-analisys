package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.InscricaoVagaService;
import br.edu.ifpb.domain.InscricaoVaga;
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

}
