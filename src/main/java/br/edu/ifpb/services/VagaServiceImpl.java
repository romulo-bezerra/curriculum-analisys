package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.Vaga;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VagaServiceImpl implements VagaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    @Override
    public void save(Vaga vaga) {
        entityManager.persist(vaga);
    }

    @Override
    public List<Idioma> extractIdiomas(String text) {
        List<Idioma> returnIdiomas = new ArrayList<>();
        String[] idiomas = text.trim().split(";");
        for (String idioma : idiomas) {
            returnIdiomas.add(new Idioma(idioma, Origem.ORIGINARIO_DO_CANDIDATO));
        }
        return returnIdiomas;
    }

    @Override
    public List<Habilidade> extractHabilidades(String text) {
        List<Habilidade> returnHabilidades = new ArrayList<>();
        String[] habilidades = text.trim().split(";");
        for (String habilidade : habilidades) {
            returnHabilidades.add(new Habilidade(habilidade, Origem.ORIGINARIO_DO_CANDIDATO));
        }
        return returnHabilidades;
    }

    @Override
    public List<Atitude> extractAtitudes(String text) {
        List<Atitude> returnAtitudes = new ArrayList<>();
        String[] atitudes = text.trim().split(";");
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
        char[] invalidCharacters = {'!', '@', '#', '$', '%', '¨', '&', '*',
             '(', ')', '-', '_', '=', '+', '\\', '|', ',', '.', ':', '/',
             '?', '°', '§', '¬', '¢', '£', '³', '²', '¹', '´', '`', '[',
             ']', '{', '}', 'ª', 'º', '^', '~', '"'};
        return (StringUtils.containsAny(text, invalidCharacters)
                || StringUtils.isBlank(text) || StringUtils.isEmpty(text));
    }

}
