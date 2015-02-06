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

import br.embrapa.cnpso.sigco.model.Facilitador;

@Named
@Stateful
@ViewScoped
public class FacilitadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Facilitador facilitador;
	private Collection<Facilitador> listaFacilitador;
	private List<Facilitador> filtroFacilitador;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.facilitador = new Facilitador();
		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Facilitador.class));

		listaFacilitador = em.createQuery(cQ).getResultList();
	}

	public Facilitador getFacilitador() {
		return facilitador;
	}

	public void setFacilitador(Facilitador facilitador) {
		this.facilitador = facilitador;
	}

	public Collection<Facilitador> getListaFacilitador() {
		return listaFacilitador;
	}

	public void setListaFacilitador(Collection<Facilitador> listaFacilitador) {
		this.listaFacilitador = listaFacilitador;
	}

	public List<Facilitador> getFiltroFacilitador() {
		return filtroFacilitador;
	}

	public void setFiltroFacilitador(List<Facilitador> filtroFacilitador) {
		this.filtroFacilitador = filtroFacilitador;
	}

	public void salvar(Facilitador facilitador) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			this.em.persist(facilitador);
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaFacilitador.jsf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Facilitador facilitador) {
		try {
			Facilitador facilita = this.em.find(Facilitador.class,
					this.facilitador.getCpf());
			this.em.remove(facilita);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Facilitador Removido",
				facilitador.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.facilitador.getCpf() != null) {
			return "Editando Facilitador";
		} else {
			return "Cadastrando Facilitador";
		}
	}
}
