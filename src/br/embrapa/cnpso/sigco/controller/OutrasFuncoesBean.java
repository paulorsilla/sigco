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

import br.embrapa.cnpso.sigco.model.OutrasFuncoes;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class OutrasFuncoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private OutrasFuncoes outrasFuncoes;
	private Collection<OutrasFuncoes> listaOutrasFuncoes;
	private List<OutrasFuncoes> filtroOutrasFuncoes;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() {
		this.outrasFuncoes = new OutrasFuncoes();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(OutrasFuncoes.class));

		listaOutrasFuncoes = em.createQuery(cQ).getResultList();
	}

	public OutrasFuncoes getOutrasFuncoes() {
		return outrasFuncoes;
	}

	public void setOutrasFuncoes(OutrasFuncoes outrasFuncoes) {
		this.outrasFuncoes = outrasFuncoes;
	}

	public Collection<OutrasFuncoes> getListaOutrasFuncoes() {
		return listaOutrasFuncoes;
	}

	public void setListaOutrasFuncoes(
			Collection<OutrasFuncoes> listaOutrasFuncoes) {
		this.listaOutrasFuncoes = listaOutrasFuncoes;
	}

	public List<OutrasFuncoes> getFiltroOutrasFuncoes() {
		return filtroOutrasFuncoes;
	}

	public void setFiltroOutrasFuncoes(List<OutrasFuncoes> filtroOutrasFuncoes) {
		this.filtroOutrasFuncoes = filtroOutrasFuncoes;
	}

	public void salvar(OutrasFuncoes outrasFuncoes) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.outrasFuncoes.getId() != null) {
				this.em.merge(outrasFuncoes);
			} else {
				this.em.persist(outrasFuncoes);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaOutrasFuncoes.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(OutrasFuncoes outrasFuncoes) {
		try {
			OutrasFuncoes of = this.em.find(OutrasFuncoes.class,
					outrasFuncoes.getId());
			this.em.remove(of);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Outras Funções Removido",
				outrasFuncoes.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.outrasFuncoes.getId() != null) {
			return "Editando Outras Funções";
		} else {
			return "Cadastrando Outras Funções";
		}
	}
}
