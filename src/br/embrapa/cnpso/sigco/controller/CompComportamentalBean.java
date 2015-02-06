package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.primefaces.event.RowEditEvent;

import br.embrapa.cnpso.sigco.model.Comportamental;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class CompComportamentalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Comportamental comportamental;
	private Collection<Comportamental> listaComportamental;
	private List<Comportamental> filtroComportamental;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {
		this.comportamental = new Comportamental();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Comportamental.class));

		listaComportamental = em.createQuery(cQ).getResultList();
	}

	public Comportamental getComportamental() {
		return comportamental;
	}

	public void setComportamental(Comportamental comportamental) {
		this.comportamental = comportamental;
	}

	public Collection<Comportamental> getListaComportamental() {
		return listaComportamental;
	}

	public void setListaComportamental(
			Collection<Comportamental> listaComportamental) {
		this.listaComportamental = listaComportamental;
	}

	public List<Comportamental> getFiltroComportamental() {
		return filtroComportamental;
	}

	public void setFiltroComportamental(
			List<Comportamental> filtroComportamental) {
		this.filtroComportamental = filtroComportamental;
	}

	public void salvar(Comportamental comportamental) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			this.em.persist(comportamental);
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaCompComportamental.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Comportamental comportamental) {
		try {
			Comportamental cComportamental = this.em.find(Comportamental.class,
					this.comportamental.getId());
			this.em.remove(cComportamental);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.comportamental = (Comportamental) event.getObject();

		FacesMessage msg = new FacesMessage(
				"Competência Comportamental Editado",
				comportamental.getCompetencia());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(comportamental);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage(
				"Competência Comportamental Removido",
				comportamental.getCompetencia());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.comportamental.getId() != null) {
			return "Editando Comportamental";
		} else {
			return "Cadastrando Comportamental";
		}
	}

}
