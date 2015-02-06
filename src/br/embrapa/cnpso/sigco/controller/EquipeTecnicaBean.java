package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.embrapa.cnpso.sigco.model.EquipeTecnica;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

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
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.equipetecnica.getId() != null) {
				this.em.merge(equipetecnica);
			} else {
				this.em.persist(equipetecnica);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaEquipeTecnica.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
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

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Equipe Técnica Removido",
				equipetecnica.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.equipetecnica.getId() != null) {
			return "Editando Equipe Técnica";
		} else {
			return "Cadastrando Equipe Técnica";
		}
	}

}
