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

@Named
@Stateful
@ViewScoped
public class FuncaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Area area;
	private Subarea subarea;
	private Collection<Area> listaArea;
	private List<Area> filtroArea;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.area = new Area();

		Query query = em.createQuery(
				"SELECT ar FROM Area ar ORDER BY ar.descricao", Area.class);
		this.listaArea = query.getResultList();
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Subarea getSubarea() {
		return subarea;
	}

	public void setSubarea(Subarea subarea) {
		this.subarea = subarea;
	}

	public Collection<Area> getListaArea() {
		return listaArea;
	}

	public void setListaArea(Collection<Area> listaArea) {
		this.listaArea = listaArea;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Area> getFiltroArea() {
		return filtroArea;
	}

	public void setFiltroArea(List<Area> filtroArea) {
		this.filtroArea = filtroArea;
	}

	public void salvar(Area area) {
		try {
			this.em.persist(area);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Area area) {

		try {
			Area ar = this.em.find(Area.class, area.getId());
			this.em.remove(ar);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.area = (Area) event.getObject();

		FacesMessage msg = new FacesMessage("Área Editado", area.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(area.getId());
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Área Cancelado",
				((Area) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
