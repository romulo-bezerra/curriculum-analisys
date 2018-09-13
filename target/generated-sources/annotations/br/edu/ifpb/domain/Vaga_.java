package br.edu.ifpb.domain;

import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import br.edu.ifpb.domain.InscricaoVaga;
import br.edu.ifpb.enums.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-13T15:46:55")
@StaticMetamodel(Vaga.class)
public class Vaga_ { 

    public static volatile SingularAttribute<Vaga, Estado> estado;
    public static volatile SingularAttribute<Vaga, Double> salario;
    public static volatile SingularAttribute<Vaga, String> titulo;
    public static volatile SingularAttribute<Vaga, String> visaoDaEmpresa;
    public static volatile ListAttribute<Vaga, InscricaoVaga> inscricoesVagas;
    public static volatile SingularAttribute<Vaga, String> descricao;
    public static volatile ListAttribute<Vaga, Idioma> idiomas;
    public static volatile ListAttribute<Vaga, Atitude> atitudes;
    public static volatile SingularAttribute<Vaga, String> dataMaxInscricao;
    public static volatile SingularAttribute<Vaga, String> horasDiarias;
    public static volatile SingularAttribute<Vaga, String> missaoDaEmpresa;
    public static volatile SingularAttribute<Vaga, Integer> id;
    public static volatile SingularAttribute<Vaga, Integer> numeroVagas;
    public static volatile ListAttribute<Vaga, Habilidade> habilidades;

}