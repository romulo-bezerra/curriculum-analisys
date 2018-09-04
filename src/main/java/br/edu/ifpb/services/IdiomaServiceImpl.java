package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.IdiomaService;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.enums.Origem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}