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

import br.embrapa.cnpso.sigco.model.Interesses;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class InteressesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Interesses interesses;
	private Collection<Interesses> listaInteresses;
	private List<Interesses> filtroInteresses;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	private void init() {
		this.interesses = new Interesses();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Interesses.class));

		listaInteresses = em.createQuery(cQ).getResultList();
	}

	public Interesses getInteresses() {
		return interesses;
	}

	public void setInteresses(Interesses interesses) {
		this.interesses = interesses;
	}

	public Collection<Interesses> getListaInteresses() {
		return listaInteresses;
	}

	public void setListaInteresses(Collection<Interesses> listaInteresses) {
		this.listaInteresses = listaInteresses;
	}

	public List<Interesses> getFiltroInteresses() {
		return filtroInteresses;
	}

	public void setFiltroInteresses(List<Interesses> filtroInteresses) {
		this.filtroInteresses = filtroInteresses;
	}

	public void salvar(Interesses interesses) {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.interesses.getId() != null) {
				this.em.merge(interesses);
			} else {
				this.em.persist(interesses);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaInteresses.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Interesses interesses) {
		try {
			Interesses inter = this.em.find(Interesses.class,
					interesses.getId());
			this.em.remove(inter);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Interesse Removido",
				interesses.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.interesses.getId() != null) {
			return "Editando Interesses";
		} else {
			return "Cadastrando Interesses";
		}
	}
}
