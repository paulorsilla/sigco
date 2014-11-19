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

import br.embrapa.cnpso.sigco.model.Empregado;
import br.embrapa.cnpso.sigco.model.EquipeTecnica;

@Named
@Stateful
@ViewScoped
public class EquipeTecnicaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private EquipeTecnica equipetecnica;
	private Collection<EquipeTecnica> listaEquipeTecnica;
	private List<EquipeTecnica> filtroEquipeTecnica;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.equipetecnica = new EquipeTecnica();

		// Query query = em.createQuery(
		// "SELECT et FROM EquipeTecnica et ORDER BY et.descricao",
		// EquipeTecnica.class);
		// this.listaEquipeTecnica = query.getResultList();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(EquipeTecnica.class));

		listaEquipeTecnica = em.createQuery(cQ).getResultList();
	}

	public EquipeTecnica getEquipetecnica() {
		return equipetecnica;
	}

	public void setEquipetecnica(EquipeTecnica equipetecnica) {
		this.equipetecnica = equipetecnica;
	}

	public Collection<EquipeTecnica> getListaEquipeTecnica() {
		return listaEquipeTecnica;
	}

	public void setListaEquipeTecnica(
			Collection<EquipeTecnica> listaEquipeTecnica) {
		this.listaEquipeTecnica = listaEquipeTecnica;
	}

	public List<EquipeTecnica> getFiltroEquipeTecnica() {
		return filtroEquipeTecnica;
	}

	public void setFiltroEquipeTecnica(List<EquipeTecnica> filtroEquipeTecnica) {
		this.filtroEquipeTecnica = filtroEquipeTecnica;
	}

	public void salvar(EquipeTecnica equipetecnica) {

		try {
			this.em.persist(equipetecnica);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(EquipeTecnica equipetecnica) {

		try {
			EquipeTecnica equi = this.em.find(EquipeTecnica.class,
					this.equipetecnica.getId());
			this.em.remove(equi);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.equipetecnica = (EquipeTecnica) event.getObject();

		FacesMessage msg = new FacesMessage("Equipe Técnica Editado",
				equipetecnica.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(equipetecnica);
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
