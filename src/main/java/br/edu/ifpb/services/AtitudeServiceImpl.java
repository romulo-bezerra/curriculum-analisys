package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.AtitudeService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<Atitude> findRandomAttitudes() {
        String querySql = "SELECT a.id, a.atitude, a.origem FROM Atitude a "
                + "WHERE origem='ORIGINARIO_DA_APLICACAO' "
                + "ORDER BY random() LIMIT 10";
        Query query = entityManager.createNativeQuery(querySql, Atitude.class);

        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

    @Override
    public Atitude findById(int id) {
        return entityManager.find(Atitude.class, id);
    }

}
