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

import br.embrapa.cnpso.sigco.model.Area;
import br.embrapa.cnpso.sigco.model.Subarea;

@Stateful
@Named
@ViewScoped
public class SubareaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Subarea subarea;
	private Area area;
	private Collection<Subarea> listaSubarea;
	private List<Subarea> filtroSubarea;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.subarea = new Subarea();

		Query query = em
				.createQuery("SELECT s FROM Subarea s ORDER BY s.descricao");
		this.listaSubarea = query.getResultList();

	}

	public Subarea getSubarea() {
		return subarea;
	}

	public void setSubarea(Subarea subarea) {
		this.subarea = subarea;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Collection<Subarea> getListaSubarea() {
		return listaSubarea;
	}

	public void setListaSubarea(Collection<Subarea> listaSubarea) {
		this.listaSubarea = listaSubarea;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Subarea> getFiltroSubarea() {
		return filtroSubarea;
	}

	public void setFiltroSubarea(List<Subarea> filtroSubarea) {
		this.filtroSubarea = filtroSubarea;
	}

	@SuppressWarnings("unchecked")
	public Collection<Area> listaArea() {

		Query query = em.createQuery("SELECT a FROM Area a");
		return query.getResultList();

	}

	public void salvar(Subarea subarea) {

		try {
			this.em.persist(subarea);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Subarea subarea) {

		try {
			Subarea suba = this.em.find(Subarea.class, subarea.getId());
			this.em.remove(suba);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.subarea = (Subarea) event.getObject();

		FacesMessage msg = new FacesMessage("Subárea Editado",
				subarea.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(subarea);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Subárea Cancelado",
				((Area) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
