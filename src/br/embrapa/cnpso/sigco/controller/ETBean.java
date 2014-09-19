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

import br.embrapa.cnpso.sigco.model.EquipeTecnica;

@Named
@Stateful
@ViewScoped
public class ETBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private EquipeTecnica et;
	private Collection<EquipeTecnica> listaET;
	private List<EquipeTecnica> filtroET;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.et = new EquipeTecnica();

		Query query = em.createQuery(
				"SELECT et FROM EquipeTecnica et ORDER BY et.descricao",
				EquipeTecnica.class);
		this.listaET = query.getResultList();
	}

	public EquipeTecnica getEt() {
		return et;
	}

	public void setEt(EquipeTecnica et) {
		this.et = et;
	}

	public Collection<EquipeTecnica> getListaET() {
		return listaET;
	}

	public void setListaET(Collection<EquipeTecnica> listaET) {
		this.listaET = listaET;
	}

	public List<EquipeTecnica> getFiltroET() {
		return filtroET;
	}

	public void setFiltroET(List<EquipeTecnica> filtroET) {
		this.filtroET = filtroET;
	}

	public void salvar(EquipeTecnica et) {
		try {
			this.em.persist(et);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(EquipeTecnica et) {

		try {
			EquipeTecnica equi = this.em.find(EquipeTecnica.class, et.getId());
			this.em.remove(equi);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.et = (EquipeTecnica) event.getObject();

		FacesMessage msg = new FacesMessage("Equipe Técnica Editado",
				et.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(et.getId());
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Equipe Técnica Cancelado",
				((EquipeTecnica) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
