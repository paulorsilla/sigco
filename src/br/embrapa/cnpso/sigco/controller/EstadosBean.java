package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.embrapa.cnpso.sigco.model.Estados;

@Stateful
@Named
@ViewScoped
public class EstadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Collection<Estados> listaEstados() {

		Query query = em.createQuery("SELECT e FROM Estados ORDER BY e.uf",
				Estados.class);
		return query.getResultList();
	}

}
