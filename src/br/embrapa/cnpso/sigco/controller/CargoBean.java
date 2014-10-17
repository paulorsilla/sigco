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

import br.embrapa.cnpso.sigco.model.Cargo;

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
	@SuppressWarnings("unchecked")
	public void init() {
		this.cargo = new Cargo();

		Query query = this.em.createQuery(
				"SELECT ca FROM Cargo ca ORDER BY ca.descricao", Cargo.class);
		this.listaCargo = query.getResultList();
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
			this.em.persist(cargo);
			this.em.flush();
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

	public void onRowEdit(RowEditEvent event) {
		this.cargo = (Cargo) event.getObject();

		FacesMessage msg = new FacesMessage("Cargo Editado",
				cargo.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(cargo);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cargo Cancelado",
				((Cargo) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
