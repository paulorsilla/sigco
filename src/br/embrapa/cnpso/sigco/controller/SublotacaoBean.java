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

import br.embrapa.cnpso.sigco.model.Sublotacao;

@Named
@Stateful
@ViewScoped
public class SublotacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Sublotacao sublotacao;
	private Collection<Sublotacao> listaSublotacao;
	private List<Sublotacao> filtroSublotacao;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.sublotacao = new Sublotacao();

		Query query = em.createQuery(
				"SELECT sl FROM Sublotacao sl ORDER BY sl.descricao",
				Sublotacao.class);
		this.listaSublotacao = query.getResultList();
	}

	public Sublotacao getSublotacao() {
		return sublotacao;
	}

	public void setSublotacao(Sublotacao sublotacao) {
		this.sublotacao = sublotacao;
	}

	public Collection<Sublotacao> getListaSublotacao() {
		return listaSublotacao;
	}

	public void setListaSublotacao(Collection<Sublotacao> listaSublotacao) {
		this.listaSublotacao = listaSublotacao;
	}

	public List<Sublotacao> getFiltroSublotacao() {
		return filtroSublotacao;
	}

	public void setFiltroSublotacao(List<Sublotacao> filtroSublotacao) {
		this.filtroSublotacao = filtroSublotacao;
	}

	public void salvar(Sublotacao sublotacao) {
		try {
			this.em.persist(sublotacao);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Sublotacao sublotacao) {
		try {
			Sublotacao subl = this.em.find(Sublotacao.class,
					this.sublotacao.getId());
			this.em.remove(subl);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.sublotacao = (Sublotacao) event.getObject();

		FacesMessage msg = new FacesMessage("Sublotação Editada",
				sublotacao.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(sublotacao.getId());
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Sublotação Cancelado",
				((Sublotacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
