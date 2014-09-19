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

import br.embrapa.cnpso.sigco.model.Atuacao;

@Named
@Stateful
@ViewScoped
public class AtuacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Atuacao atuacao;
	private Collection<Atuacao> listaAtuacao;
	private List<Atuacao> filtroAtuacao;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.atuacao = new Atuacao();

		Query query = em.createQuery(
				"SELECT at FROM Atuacao at ORDER BY at.descricao",
				Atuacao.class);
		this.listaAtuacao = query.getResultList();
	}

	public Atuacao getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(Atuacao atuacao) {
		this.atuacao = atuacao;
	}

	public List<Atuacao> getFiltroAtuacao() {
		return filtroAtuacao;
	}

	public void setFiltroAtuacao(List<Atuacao> filtroAtuacao) {
		this.filtroAtuacao = filtroAtuacao;
	}

	public Collection<Atuacao> getListaAtuacao() {
		return listaAtuacao;
	}

	public void setListaAtuacao(Collection<Atuacao> listaAtuacao) {
		this.listaAtuacao = listaAtuacao;
	}

	public void salvar(Atuacao atuacao) {
		try {
			this.em.persist(atuacao);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Atuacao atuacao) {

		try {
			Atuacao at = this.em.find(Atuacao.class, atuacao.getId());
			this.em.remove(at);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.atuacao = (Atuacao) event.getObject();

		FacesMessage msg = new FacesMessage("Atuação Editado",
				atuacao.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(atuacao.getId());
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Atuação Cancelado",
				((Atuacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
