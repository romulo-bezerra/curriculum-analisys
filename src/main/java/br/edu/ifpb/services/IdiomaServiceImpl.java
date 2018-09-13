package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.IdiomaService;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    @Override
    public List<Idioma> findAllByText(String[] array) {
        List<Idioma> idiomas = new ArrayList<>();
        for (String idiomaString : array) {

            String querySql = "SELECT i FROM Idioma i WHERE i.idioma = :idioma";
            TypedQuery<Idioma> query = entityManager.createQuery(querySql, Idioma.class);
            query.setParameter("idioma", idiomaString);

            Optional<Idioma> idioma = query.getResultList().stream().findFirst();
            if (idioma.isPresent()) {
                idiomas.add(idioma.get());
            }
        }
        return idiomas;
    }

}
