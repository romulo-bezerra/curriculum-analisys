package br.edu.ifpb.domain;

import br.edu.ifpb.domain.embeddables.Endereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T00:01:02")
@StaticMetamodel(Candidato.class)
public class Candidato_ { 

    public static volatile SingularAttribute<Candidato, Integer> idade;
    public static volatile SingularAttribute<Candidato, String> senha;
    public static volatile SingularAttribute<Candidato, String> telefone;
    public static volatile SingularAttribute<Candidato, Endereco> endereco;
    public static volatile SingularAttribute<Candidato, String> nome;
    public static volatile SingularAttribute<Candidato, Integer> id;
    public static volatile SingularAttribute<Candidato, String> sexo;
    public static volatile SingularAttribute<Candidato, String> nacionalidade;
    public static volatile SingularAttribute<Candidato, String> sobreNome;
    public static volatile SingularAttribute<Candidato, String> email;

}