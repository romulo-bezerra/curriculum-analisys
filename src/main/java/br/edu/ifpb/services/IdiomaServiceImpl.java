package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.IdiomaService;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class IdiomaServiceImpl implements IdiomaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;
    
    @Override
    public void save(Idioma idioma) {
        entityManager.persist(idioma);
    }

    @Override
    public void save(List<String> idiomas) {
        idiomas.forEach(idioma -> entityManager.persist(
                new Idioma(idioma, Origem.ORIGINARIO_DA_APLICACAO)));
    }
    
    @Override
    public List<Idioma> findRandomLanguages() {
        String querySql = "SELECT i.id, i.idioma, i.origem FROM Idioma i "
                + "WHERE origem='ORIGINARIO_DA_APLICACAO' "
                + "ORDER BY random() LIMIT 10";
        Query query = entityManager.createNativeQuery(querySql, Idioma.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

}