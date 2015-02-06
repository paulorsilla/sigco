package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.543-0200")
@StaticMetamodel(AreaAtuacao.class)
public class AreaAtuacao_ {
	public static volatile SingularAttribute<AreaAtuacao, Long> id;
	public static volatile SingularAttribute<AreaAtuacao, String> descricao;
	public static volatile CollectionAttribute<AreaAtuacao, SubareaAtuacao> subarea;
}
