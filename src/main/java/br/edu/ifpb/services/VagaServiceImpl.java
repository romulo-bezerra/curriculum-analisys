package br.edu.ifpb.services;

import br.edu.ifpb.abstractions.VagaService;
import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.Vaga;
import br.edu.ifpb.enums.Origem;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VagaServiceImpl implements VagaService {

    @PersistenceContext(unitName = "curriculum-analisys")
    EntityManager entityManager;

    static Logger log = Logger.getLogger("Log Extracts Infos");

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
        
        //Temp
        log.log(Level.INFO, "Resultado Idiomas = {0}", Arrays.toString(idiomas));
        
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
        char[] invalidCharacters = {'!', '@', '#', '$', '%', '¨', '&', '*',
            '(', ')', '-', '_', '=', '+', '\\', '|', ',', '.', ':', '/',
            '?', '°', '§', '¬', '¢', '£', '³', '²', '¹', '´', '`', '[',
            ']', '{', '}', 'ª', 'º', '^', '~', '"'};
        return (StringUtils.containsAny(text, invalidCharacters)
                || StringUtils.isBlank(text) || StringUtils.isEmpty(text));
    }

    @Override
    public List<Vaga> findAllVagas(Empresa empresa) {
        return empresa.getVagas();
    }

}
