package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.HabilidadeService;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.enums.Origem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}