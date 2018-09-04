package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.AtitudeService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.enums.Origem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AtitudeServiceImpl implements AtitudeService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;
    
    @Override
    public void save(Atitude atitude) {
        entityManager.persist(atitude);
    }

    @Override
    public void save(List<String> atitudes) {
        atitudes.forEach(atitude -> entityManager.persist(
                new Atitude(atitude, Origem.ORIGINARIO_DA_APLICACAO)));
    }

}