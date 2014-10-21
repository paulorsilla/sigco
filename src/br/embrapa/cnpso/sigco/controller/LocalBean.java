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

import br.embrapa.cnpso.sigco.model.Localizacao;

@Named
@Stateful
@ViewScoped
public class LocalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Localizacao local;
	private Collection<Localizacao> listaLocal;
	private List<Localizacao> filtroLocal;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.local = new Localizacao();

		Query query = em.createQuery(
				"SELECT local FROM Localizacao local ORDER BY local.descricao",
				Localizacao.class);
		this.listaLocal = query.getResultList();
	}

	public Localizacao getLocal() {
		return local;
	}

	public void setLocal(Localizacao local) {
		this.local = local;
	}

	public Collection<Localizacao> getListaLocal() {
		return listaLocal;
	}

	public void setListaLocal(Collection<Localizacao> listaLocal) {
		this.listaLocal = listaLocal;
	}

	public List<Localizacao> getFiltroLocal() {
		return filtroLocal;
	}

	public void setFiltroLocal(List<Localizacao> filtroLocal) {
		this.filtroLocal = filtroLocal;
	}

	public void salvar(Localizacao local) {
		try {
			this.em.persist(local);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Localizacao local) {

		try {
			Localizacao loc = this.em.find(Localizacao.class, local.getId());
			this.em.remove(loc);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.local = (Localizacao) event.getObject();

		FacesMessage msg = new FacesMessage("Localização Editado",
				local.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			em.merge(local);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Área Cancelado",
				((Localizacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
