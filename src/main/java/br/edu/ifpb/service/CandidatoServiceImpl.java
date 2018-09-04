package br.edu.ifpb.service;

import br.edu.ifpb.abstraction.CandidatoService;
import br.edu.ifpb.domain.Candidato;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CandidatoServiceImpl implements CandidatoService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;
    
    @Override
    public void save(Candidato candidato) {
        entityManager.persist(candidato);
    }

    @Override
    public boolean isRegistered(String email) {
        TypedQuery<Candidato> query = entityManager
                .createQuery("SELECT c FROM Candidato c "
                        + "WHERE c.email= :email", Candidato.class);
        query.setParameter("email", email);
        Optional<Candidato> candidato = query.getResultList().stream().findFirst();
        return candidato.isPresent();
    }

}