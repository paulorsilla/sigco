package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.embrapa.cnpso.sigco.model.Autorizacao;

@Stateful
@Named
@ViewScoped
public class AutorizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Collection<Autorizacao> listaAutorizacoes() {

		Query query = em.createQuery("SELECT a FROM Autorizacao a");
		return query.getResultList();

	}
}
