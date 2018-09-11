package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.EmpresaService;
import br.edu.ifpb.domain.Empresa;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Empresa> findAllByAttributes(String infoRelated) {
        String querySql = "SELECT e FROM Empresa e "
                + "WHERE UPPER(e.nomeFantasia) LIKE :infoRelated "
                + "OR UPPER(e.cnpj) LIKE :infoRelated "
                + "OR UPPER(e.empresario) LIKE :infoRelated "
                + "OR UPPER(e.razaoSocial) LIKE :infoRelated ";

        TypedQuery<Empresa> query = entityManager.createQuery(querySql, Empresa.class);
        query.setParameter("infoRelated", "%" + infoRelated.toUpperCase() + "%");

        Optional<Empresa> empresa = query.getResultList().stream().findFirst();
        if (empresa.isPresent()) {
            return query.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Empresa> findAll() {
        String querySql = "SELECT e FROM Empresa e";

        TypedQuery<Empresa> query = entityManager.createQuery(querySql, Empresa.class);

        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

}
