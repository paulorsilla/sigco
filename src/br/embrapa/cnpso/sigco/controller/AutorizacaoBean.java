package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.embrapa.cnpso.sigco.model.Autorizacao;
import br.embrapa.cnpso.sigco.model.Empregado;

@Stateful
@Named
@ViewScoped
public class AutorizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Autorizacao> listaAutorizacoes() {

		// Query query = em.createQuery("SELECT a FROM Autorizacao a");
		// return query.getResultList();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Autorizacao.class));

		return em.createQuery(cQ).getResultList();

	}
}
