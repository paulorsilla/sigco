package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.659-0200")
@StaticMetamodel(Hobbies.class)
public class Hobbies_ {
	public static volatile SingularAttribute<Hobbies, Integer> id;
	public static volatile SingularAttribute<Hobbies, String> descricao;
	public static volatile ListAttribute<Hobbies, Empregado> empregado;
}
