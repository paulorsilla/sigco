package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.641-0200")
@StaticMetamodel(Competencia.class)
public class Competencia_ {
	public static volatile SingularAttribute<Competencia, Long> id;
	public static volatile SingularAttribute<Competencia, String> competencia;
	public static volatile CollectionAttribute<Competencia, Empregado> empregado;
}
