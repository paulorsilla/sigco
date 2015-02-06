package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.674-0200")
@StaticMetamodel(TipoCompetenciaTecnica.class)
public class TipoCompetenciaTecnica_ {
	public static volatile SingularAttribute<TipoCompetenciaTecnica, Long> id;
	public static volatile SingularAttribute<TipoCompetenciaTecnica, String> descricao;
	public static volatile SingularAttribute<TipoCompetenciaTecnica, Tecnica> tecnica;
}
