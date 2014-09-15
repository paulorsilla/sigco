package br.embrapa.cnpso.sigco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-22T14:01:37.014-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, String> login;
	public static volatile SingularAttribute<Usuario, String> nomeCompleto;
	public static volatile SingularAttribute<Usuario, Boolean> enable;
	public static volatile SingularAttribute<Usuario, Autorizacao> autorizacao;
}
