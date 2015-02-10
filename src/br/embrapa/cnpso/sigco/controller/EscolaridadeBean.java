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
import javax.persistence.criteria.Root;

import br.embrapa.cnpso.sigco.model.Escolaridade;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class EscolaridadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Escolaridade escolaridade;
	private Collection<Escolaridade> listaEscolaridade;
	private List<Escolaridade> filtroEscolaridade;

	@PostConstruct
	public void init() {
		this.escolaridade = new Escolaridade();
		CriteriaQuery<Escolaridade> cQ = em.getCriteriaBuilder().createQuery(
				Escolaridade.class);
		Root<Escolaridade> from = cQ.from(Escolaridade.class);
		cQ.orderBy(em.getCriteriaBuilder().asc(from.get("ordem")));
		listaEscolaridade = em.createQuery(cQ).getResultList();
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Collection<Escolaridade> getListaEscolaridade() {
		return listaEscolaridade;
	}

	public void setListaEscolaridade(Collection<Escolaridade> listaEscolaridade) {
		this.listaEscolaridade = listaEscolaridade;
	}

	public List<Escolaridade> getFiltroEscolaridade() {
		return filtroEscolaridade;
	}

	public void setFiltroEscolaridade(List<Escolaridade> filtroEscolaridade) {
		this.filtroEscolaridade = filtroEscolaridade;
	}

	public void salvar(Escolaridade escolaridade) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.escolaridade.getId() != null) {
				this.em.merge(escolaridade);
			} else {
				this.em.persist(escolaridade);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/auth/comum/listas/listaEscolaridade.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Escolaridade escolaridade) {

		try {
			Escolaridade es = this.em.find(Escolaridade.class,
					escolaridade.getId());
			this.em.remove(es);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Escolaridade Removido",
				escolaridade.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.escolaridade.getId() != null) {
			return "Editando Escolaridade";
		} else {
			return "Cadastrando Escolaridade";
		}
	}
}
