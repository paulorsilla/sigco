package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-05T16:05:09.667-0200")
@StaticMetamodel(SubareaAtuacao.class)
public class SubareaAtuacao_ {
	public static volatile SingularAttribute<SubareaAtuacao, Long> id;
	public static volatile SingularAttribute<SubareaAtuacao, String> descricao;
	public static volatile SingularAttribute<SubareaAtuacao, AreaAtuacao> area;
}
