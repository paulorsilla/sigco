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
import javax.persistence.criteria.CriteriaQuery;

import org.primefaces.event.RowEditEvent;

import br.embrapa.cnpso.sigco.model.AreaAtuacao;
import br.embrapa.cnpso.sigco.model.SubareaAtuacao;

@Stateful
@Named
@ViewScoped
public class SubareaAtuacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private SubareaAtuacao subarea;
	private AreaAtuacao area;
	private Collection<SubareaAtuacao> listaSubarea;
	private List<SubareaAtuacao> filtroSubarea;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.subarea = new SubareaAtuacao();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(SubareaAtuacao.class));

		listaSubarea = em.createQuery(cQ).getResultList();
	}

	public SubareaAtuacao getSubarea() {
		return subarea;
	}

	public void setSubarea(SubareaAtuacao subarea) {
		this.subarea = subarea;
	}

	public AreaAtuacao getArea() {
		return area;
	}

	public void setArea(AreaAtuacao area) {
		this.area = area;
	}

	public Collection<SubareaAtuacao> getListaSubarea() {
		return listaSubarea;
	}

	public void setListaSubarea(Collection<SubareaAtuacao> listaSubarea) {
		this.listaSubarea = listaSubarea;
	}

	public List<SubareaAtuacao> getFiltroSubarea() {
		return filtroSubarea;
	}

	public void setFiltroSubarea(List<SubareaAtuacao> filtroSubarea) {
		this.filtroSubarea = filtroSubarea;
	}

	@SuppressWarnings("unchecked")
	public Collection<AreaAtuacao> listaArea() {

		Query query = em.createQuery("SELECT a FROM AreaAtuacao a");
		return query.getResultList();

	}

	public void salvar(SubareaAtuacao subarea) {

		System.out.println("-> " + subarea.getDescricao());

		try {
			this.em.persist(subarea);
			this.em.flush();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/sigco/auth/comum/listas/listaSubareaAtuacao.jsf");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(SubareaAtuacao subarea) {

		try {
			SubareaAtuacao suba = this.em.find(SubareaAtuacao.class,
					this.subarea.getId());
			this.em.remove(suba);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.subarea = (SubareaAtuacao) event.getObject();

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
				((AreaAtuacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Subárea Removido",
				subarea.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
