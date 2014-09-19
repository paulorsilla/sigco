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

import br.embrapa.cnpso.sigco.model.Funcao;

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
	@SuppressWarnings("unchecked")
	public void init() {
		this.funcao = new Funcao();

		Query query = em.createQuery(
				"SELECT func FROM Funcao func ORDER BY func.descricao",
				Funcao.class);
		this.listaFuncao = query.getResultList();
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
			this.em.persist(funcao);
			this.em.flush();
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

	public void onRowEdit(RowEditEvent event) {

		this.funcao = (Funcao) event.getObject();

		FacesMessage msg = new FacesMessage("Função Editado",
				funcao.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(funcao.getId());
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Função Cancelado",
				((Funcao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
