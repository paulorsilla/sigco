package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.660-0200")
@StaticMetamodel(Instituicao.class)
public class Instituicao_ {
	public static volatile SingularAttribute<Instituicao, String> cnpj;
	public static volatile SingularAttribute<Instituicao, String> razaoSocial;
	public static volatile SingularAttribute<Instituicao, String> contato1;
	public static volatile SingularAttribute<Instituicao, String> contato2;
	public static volatile SingularAttribute<Instituicao, String> telefone;
	public static volatile SingularAttribute<Instituicao, String> email;
	public static volatile SingularAttribute<Instituicao, String> logradouro;
	public static volatile SingularAttribute<Instituicao, String> numero;
	public static volatile SingularAttribute<Instituicao, String> complemento;
	public static volatile SingularAttribute<Instituicao, String> cep;
	public static volatile SingularAttribute<Instituicao, String> cidade;
	public static volatile SingularAttribute<Instituicao, Estados> uf;
}
