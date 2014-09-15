package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-12T15:08:54.538-0300")
@StaticMetamodel(Area.class)
public class Area_ {
	public static volatile SingularAttribute<Area, Long> id;
	public static volatile SingularAttribute<Area, String> descricao;
	public static volatile CollectionAttribute<Area, Subarea> subarea;
}
