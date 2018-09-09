package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.EmpresaService;
import br.edu.ifpb.domain.Empresa;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EmpresaServiceImpl implements EmpresaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;
    
    @Override
    public void save(Empresa empresa) {
        entityManager.persist(empresa);
    }

    @Override
    public boolean isRegistered(String email) {
        TypedQuery<Empresa> query = entityManager
                .createQuery("SELECT e FROM Empresa e "
                        + "WHERE e.email= :email", Empresa.class);
        query.setParameter("email", email);
        Optional<Empresa> empresa = query.getResultList().stream().findFirst();
        return empresa.isPresent();
    }

    @Override
    public void update(Empresa novoEstado) {
        entityManager.merge(novoEstado);
    }

}