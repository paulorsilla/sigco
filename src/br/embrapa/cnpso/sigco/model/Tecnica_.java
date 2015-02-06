package br.embrapa.cnpso.sigco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.673-0200")
@StaticMetamodel(Tecnica.class)
public class Tecnica_ extends Competencia_ {
	public static volatile SingularAttribute<Tecnica, Integer> cargaHoraria;
	public static volatile SingularAttribute<Tecnica, Date> periodo;
	public static volatile SingularAttribute<Tecnica, Boolean> acaoCorporativa;
	public static volatile CollectionAttribute<Tecnica, TipoCompetenciaTecnica> tipoCompTecnica;
	public static volatile CollectionAttribute<Tecnica, Instituicao> instituicao;
}
