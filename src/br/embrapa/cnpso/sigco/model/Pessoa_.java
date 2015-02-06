package br.embrapa.cnpso.sigco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.666-0200")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ {
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Date> dataNasc;
	public static volatile SingularAttribute<Pessoa, String> email;
	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, String> celular;
}
