package br.embrapa.cnpso.sigco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.648-0200")
@StaticMetamodel(Empregado.class)
public class Empregado_ extends Pessoa_ {
	public static volatile SingularAttribute<Empregado, String> matricula;
	public static volatile SingularAttribute<Empregado, Date> dataAdmissao;
	public static volatile SingularAttribute<Empregado, Date> dataDesligamento;
	public static volatile SingularAttribute<Empregado, Boolean> situacao;
	public static volatile CollectionAttribute<Empregado, Competencia> competencia;
}
