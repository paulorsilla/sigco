package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.event.RowEditEvent;

import br.embrapa.cnpso.sigco.model.Escolaridade;

@Named
@Stateful
@ViewScoped
public class EscolaridadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Escolaridade escolaridade;
	private Collection<Escolaridade> listaEscolaridade;
	private List<Escolaridade> filtroEscolaridade;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.escolaridade = new Escolaridade();

		Query query = em.createQuery(
				"SELECT es FROM Escolaridade es ORDER BY es.ordem",
				Escolaridade.class);
		this.listaEscolaridade = query.getResultList();
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Collection<Escolaridade> getListaEscolaridade() {
		return listaEscolaridade;
	}

	public void setListaEscolaridade(Collection<Escolaridade> listaEscolaridade) {
		this.listaEscolaridade = listaEscolaridade;
	}

	public List<Escolaridade> getFiltroEscolaridade() {
		return filtroEscolaridade;
	}

	public void setFiltroEscolaridade(List<Escolaridade> filtroEscolaridade) {
		this.filtroEscolaridade = filtroEscolaridade;
	}

	public void salvar(Escolaridade escolaridade) {
		try {
			this.em.persist(escolaridade);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Escolaridade escolaridade) {

		try {
			Escolaridade es = this.em.find(Escolaridade.class,
					escolaridade.getId());
			this.em.remove(es);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.escolaridade = (Escolaridade) event.getObject();

		FacesMessage msg = new FacesMessage("Escolaridade Editado",
				escolaridade.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(escolaridade);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Escolaridade Cancelado",
				((Escolaridade) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
