package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.HabilidadeService;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HabilidadeServiceImpl implements HabilidadeService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    @Override
    public void save(Habilidade habilidade) {
        entityManager.persist(habilidade);
    }

    @Override
    public void save(List<String> habilidades) {
        habilidades.forEach(habilidade -> entityManager.persist(
                new Habilidade(habilidade, Origem.ORIGINARIO_DA_APLICACAO)));
    }

    @Override
    public List<Habilidade> findRandomAbiliity() {
        String querySql = "SELECT h.id, h.habilidade, h.origem FROM Habilidade h "
                + "WHERE origem='ORIGINARIO_DA_APLICACAO' "
                + "ORDER BY random() LIMIT 10";
        Query query = entityManager.createNativeQuery(querySql, Habilidade.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

}
