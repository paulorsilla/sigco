package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-06T09:05:47.530-0200")
@StaticMetamodel(Interesses.class)
public class Interesses_ {
	public static volatile SingularAttribute<Interesses, Long> id;
	public static volatile SingularAttribute<Interesses, String> descricao;
	public static volatile ListAttribute<Interesses, Empregado> empregado;
}
