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

import br.embrapa.cnpso.sigco.model.Tecnica;

@Named
@Stateful
@ViewScoped
public class CompTecnicaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Tecnica compTecnica;
	private Collection<Tecnica> listaTecnica;
	private List<Tecnica> filtroTecnica;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Init() {
		this.compTecnica = new Tecnica();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Tecnica.class));

		listaTecnica = em.createQuery(cQ).getResultList();
	}

	public Tecnica getCompTecnica() {
		return compTecnica;
	}

	public void setCompTecnica(Tecnica compTecnica) {
		this.compTecnica = compTecnica;
	}

	public Collection<Tecnica> getListaTecnica() {
		return listaTecnica;
	}

	public void setListaTecnica(Collection<Tecnica> listaTecnica) {
		this.listaTecnica = listaTecnica;
	}

	public List<Tecnica> getFiltroTecnica() {
		return filtroTecnica;
	}

	public void setFiltroTecnica(List<Tecnica> filtroTecnica) {
		this.filtroTecnica = filtroTecnica;
	}

	public void salvar(Tecnica tecnica) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			this.em.persist(tecnica);
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaCompTecnica.jsf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.Init();
		}
	}

	public void excluir(Tecnica tecnica) {
		try {
			Tecnica cTecnica = this.em.find(Tecnica.class,
					this.compTecnica.getId());
			this.em.remove(cTecnica);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.Init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.compTecnica = (Tecnica) event.getObject();

		FacesMessage msg = new FacesMessage("Competência Técnica Editado",
				compTecnica.getCompetencia());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(compTecnica);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Competência Técnica Cancelado",
				((Tecnica) event.getObject()).getCompetencia());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Competência Técnica Removido",
				compTecnica.getCompetencia());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.compTecnica.getId() != null) {
			return "Editando Competência Técnica";
		} else {
			return "Cadastrando Competência Técnica";
		}
	}

}
