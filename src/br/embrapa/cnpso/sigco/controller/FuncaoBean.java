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

import br.embrapa.cnpso.sigco.model.Funcao;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class FuncaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Funcao funcao;
	private Collection<Funcao> listaFuncao;
	private List<Funcao> filtroFuncao;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.funcao = new Funcao();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Funcao.class));

		listaFuncao = em.createQuery(cQ).getResultList();
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Collection<Funcao> getListaFuncao() {
		return listaFuncao;
	}

	public void setListaFuncao(Collection<Funcao> listaFuncao) {
		this.listaFuncao = listaFuncao;
	}

	public List<Funcao> getFiltroFuncao() {
		return filtroFuncao;
	}

	public void setFiltroFuncao(List<Funcao> filtroFuncao) {
		this.filtroFuncao = filtroFuncao;
	}

	public void salvar(Funcao funcao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.funcao.getId() != null) {
				this.em.merge(funcao);
			} else {
				this.em.persist(funcao);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/sigco/auth/comum/listas/listaFuncao.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Funcao funcao) {

		try {
			Funcao func = this.em.find(Funcao.class, funcao.getId());
			this.em.remove(func);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Função Removido",
				funcao.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.funcao.getId() != null) {
			return "Editando Função";
		} else {
			return "Cadastrando Função";
		}
	}
}
