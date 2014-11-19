package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.embrapa.cnpso.sigco.model.Estados;

@Stateful
@Named
@ViewScoped
public class EstadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Estados> listaEstados() {

		// Query query =
		// em.createQuery("SELECT e FROM Estados e ORDER BY e.uf");
		// return query.getResultList();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Estados.class));

		return em.createQuery(cQ).getResultList();
	}

}
