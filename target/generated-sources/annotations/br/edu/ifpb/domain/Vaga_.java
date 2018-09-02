package br.edu.ifpb.domain;

import br.edu.ifpb.domain.Atitude;
import br.edu.ifpb.domain.Habilidade;
import br.edu.ifpb.domain.Idioma;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-02T08:35:09")
@StaticMetamodel(Vaga.class)
public class Vaga_ { 

    public static volatile ListAttribute<Vaga, Idioma> idiomas;
    public static volatile ListAttribute<Vaga, Atitude> atitudes;
    public static volatile SingularAttribute<Vaga, ZonedDateTime> prazoInscrição;
    public static volatile SingularAttribute<Vaga, String> horasDiarias;
    public static volatile SingularAttribute<Vaga, Double> salario;
    public static volatile SingularAttribute<Vaga, String> missaoDaEmpresa;
    public static volatile SingularAttribute<Vaga, String> titulo;
    public static volatile SingularAttribute<Vaga, Integer> id;
    public static volatile SingularAttribute<Vaga, Integer> numeroVagas;
    public static volatile SingularAttribute<Vaga, String> visaoDaEmpresa;
    public static volatile SingularAttribute<Vaga, String> descricao;
    public static volatile ListAttribute<Vaga, Habilidade> habilidades;

}