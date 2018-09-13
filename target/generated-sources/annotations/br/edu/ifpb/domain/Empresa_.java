package br.edu.ifpb.domain;

import br.edu.ifpb.domain.Vaga;
import br.edu.ifpb.domain.embeddable.Endereco;
import br.edu.ifpb.enums.TipoEmpresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-13T15:46:55")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> senha;
    public static volatile SingularAttribute<Empresa, String> telefone;
    public static volatile ListAttribute<Empresa, Vaga> vagas;
    public static volatile SingularAttribute<Empresa, String> nomeFantasia;
    public static volatile SingularAttribute<Empresa, byte[]> foto;
    public static volatile SingularAttribute<Empresa, TipoEmpresa> tipoEmpresa;
    public static volatile SingularAttribute<Empresa, Endereco> endereco;
    public static volatile SingularAttribute<Empresa, Integer> id;
    public static volatile SingularAttribute<Empresa, String> cnpj;
    public static volatile SingularAttribute<Empresa, String> empresario;
    public static volatile SingularAttribute<Empresa, String> razaoSocial;
    public static volatile SingularAttribute<Empresa, String> email;

}