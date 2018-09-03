package br.edu.ifpb.service;

import br.edu.ifpb.abstraction.LoginService;
import br.edu.ifpb.domain.Candidato;
import br.edu.ifpb.domain.Empresa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LoginServiceImpl implements LoginService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    @Override
    public Candidato authenticateUser(String email, String senha) {
        TypedQuery<Candidato> query = entityManager
                .createQuery("SELECT c FROM Candidato c WHERE c.email= :email "
                        + "AND c.senha= :senha", Candidato.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        Optional<Candidato> candidato = query.getResultList().stream().findFirst();
        if (candidato.isPresent()) {
            return candidato.get();
        } else {
            return null;
        }
    }

    @Override
    public List<String> getOptionsTipoLogin() {
        List<String> optionsTipoLogin = new ArrayList<>();
        optionsTipoLogin.add("Empresa");
        optionsTipoLogin.add("Candidato");
        return optionsTipoLogin;
    }

    @Override
    public Empresa authenticateCompany(String email, String senha) {
        TypedQuery<Empresa> query = entityManager
                .createQuery("SELECT e FROM Empresa e WHERE e.email= :email "
                        + "AND e.senha= :senha", Empresa.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        Optional<Empresa> empresa = query.getResultList().stream().findFirst();
        if (empresa.isPresent()) {
            return empresa.get();
        } else {
            return null;
        }
    }
    
}