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
//import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.embrapa.cnpso.sigco.model.Cargo;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class CargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Cargo cargo;
	private Collection<Cargo> listaCargo;
	private List<Cargo> filtroCargo;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.cargo = new Cargo();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Cargo.class));

		listaCargo = em.createQuery(cQ).getResultList();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Collection<Cargo> getListaCargo() {
		return listaCargo;
	}

	public void setListaCargo(Collection<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public List<Cargo> getFiltroCargo() {
		return filtroCargo;
	}

	public void setFiltroCargo(List<Cargo> filtroCargo) {
		this.filtroCargo = filtroCargo;
	}

	public void salvar(Cargo cargo) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.cargo.getId() != null) {
				this.em.merge(cargo);
			} else {
				this.em.persist(cargo);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/auth/comum/listas/listaCargo.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Cargo cargo) {
		try {
			Cargo ca = this.em.find(Cargo.class, this.cargo.getId());
			this.em.remove(ca);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Cargo Removido",
				cargo.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.cargo.getId() != null) {
			return "Editando Cargo";
		} else {
			return "Cadastrando Cargo";
		}
	}

}
