package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.domain.Vaga;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class VagaServiceImpl implements VagaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    @Override

    public void save(Vaga vaga) {
        entityManager.persist(vaga);
    }

    @Override
    public void update(Vaga novoEstado) {
        entityManager.merge(novoEstado);
    }

    @Override
    public List<Idioma> extractIdiomas(String text) {
        List<Idioma> returnIdiomas = new ArrayList<>();
        String textTrim = text.replace(" ", "");
        String[] idiomas = textTrim.split(";");

        for (String idioma : idiomas) {
            returnIdiomas.add(new Idioma(idioma, Origem.ORIGINARIO_DO_CANDIDATO));
        }
        return returnIdiomas;
    }

    @Override
    public List<Habilidade> extractHabilidades(String text) {
        List<Habilidade> returnHabilidades = new ArrayList<>();
        String textTrim = text.replace(" ", "");
        String[] habilidades = textTrim.split(";");
        for (String habilidade : habilidades) {
            returnHabilidades.add(new Habilidade(habilidade, Origem.ORIGINARIO_DO_CANDIDATO));
        }
        return returnHabilidades;
    }

    @Override
    public List<Atitude> extractAtitudes(String text) {
        List<Atitude> returnAtitudes = new ArrayList<>();
        String textTrim = text.replace(" ", "");
        String[] atitudes = textTrim.split(";");
        for (String atitude : atitudes) {
            returnAtitudes.add(new Atitude(atitude, Origem.ORIGINARIO_DO_CANDIDATO));
        }
        return returnAtitudes;
    }

    @Override
    public boolean containNumber(String text) {
        return StringUtils.isNumeric(text);
    }

    @Override
    public boolean containInvalidCharacter(String text) {
        char[] invalidCharacters = {'@', '#', '$', '%', '¨', '&', '*',
            '_', '=', '\\', '|', '/', '?', '§', '¬', '¢', '£', '³',
            '²', '¹', '´', '`', '[', ']', '{', '}', 'ª', 'º'};
        return (StringUtils.containsAny(text, invalidCharacters));
    }

    @Override
    public boolean isEmpty(String text) {
        return (StringUtils.isBlank(text) || StringUtils.isEmpty(text));
    }

    @Override
    public List<Vaga> findAllVagas(Empresa empresa) {
        return empresa.getVagas();
    }

    @Override
    public List<Atitude> getAtitudes(Vaga vaga) {
        return vaga.getAtitudes();
    }

    @Override
    public List<Habilidade> getHabilidades(Vaga vaga) {
        return vaga.getHabilidades();
    }

    @Override
    //Concertar idomas (retirar o 'avançado') e verificar se existem iguais lstados
    public List<Idioma> getIdioma(Vaga vaga) {
        return vaga.getIdiomas();
    }

    @Override
    public List<Vaga> findAllWithInscricao(Empresa empresa) {
        List<Vaga> vagasComInscricoes = new ArrayList<>();
        for (Vaga vaga : empresa.getVagas()) {
            if (!vaga.getInscricoesVagas().isEmpty()) {
                vagasComInscricoes.add(vaga);
            }
        }
        return vagasComInscricoes;
    }

    @Override
    public List<Vaga> findAllContains(int idCandidato) {
        List<Vaga> vagasInscritas = new ArrayList<>();

        String sql = "SELECT v FROM Vaga v WHERE v.inscricoesVagas IS NOT EMPTY";
        TypedQuery<Vaga> query = entityManager.createQuery(sql, Vaga.class);

        if (query.getResultList() != null) {
            for (Vaga vaga : query.getResultList()) {
                for (InscricaoVaga inscricaoVaga : vaga.getInscricoesVagas()) {
                    if (inscricaoVaga.getCandidato().getId() == idCandidato) {
                        vagasInscritas.add(vaga);
                        break;
                    }
                }
            }
            return vagasInscritas;
        }
        return new ArrayList<>();
    }
    
    @Override
    public boolean isRegistered(String titulo) {
        String querySql = "SELECT v FROM Vaga v WHERE v.titulo = :titulo ";
        TypedQuery<Vaga> query = entityManager.createQuery(querySql, Vaga.class);
        query.setParameter("titulo", titulo);
        Optional<Vaga> vaga = query.getResultList().stream().findFirst();
        return vaga.isPresent();
    }

}
