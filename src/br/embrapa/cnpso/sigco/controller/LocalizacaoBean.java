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

import br.embrapa.cnpso.sigco.model.Localizacao;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class LocalizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Localizacao localizacao;
	private Collection<Localizacao> listaLocalizacao;
	private List<Localizacao> filtroLocalizacao;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.localizacao = new Localizacao();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Localizacao.class));

		listaLocalizacao = em.createQuery(cQ).getResultList();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Collection<Localizacao> getListaLocalizacao() {
		return listaLocalizacao;
	}

	public void setListaLocal(Collection<Localizacao> listaLocalizacao) {
		this.listaLocalizacao = listaLocalizacao;
	}

	public List<Localizacao> getFiltroLocalizacao() {
		return filtroLocalizacao;
	}

	public void setFiltroLocal(List<Localizacao> filtroLocalizacao) {
		this.filtroLocalizacao = filtroLocalizacao;
	}

	public void salvar(Localizacao localizacao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();

			if (this.localizacao.getId() != null) {
				this.em.merge(localizacao);
			} else {
				this.em.persist(localizacao);
			}
			this.em.flush();
			this.localizacao = null;
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaLocalizacao.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();

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

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Localização Removido",
				localizacao.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.localizacao.getId() != null) {
			return "Editando Localização";
		} else {
			return "Cadastrando Localização";
		}
	}
}
