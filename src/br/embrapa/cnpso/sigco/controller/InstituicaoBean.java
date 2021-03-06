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

import org.primefaces.event.RowEditEvent;

import br.embrapa.cnpso.sigco.model.Estados;
import br.embrapa.cnpso.sigco.model.Instituicao;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class InstituicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Instituicao instituicao;
	private Estados estados;
	private Collection<Instituicao> listaInstituicao;
	private List<Instituicao> filtroInstituicao;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.instituicao = new Instituicao();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Instituicao.class));

		listaInstituicao = em.createQuery(cQ).getResultList();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Collection<Instituicao> getListaInstituicao() {
		return listaInstituicao;
	}

	public void setListaInstituicao(Collection<Instituicao> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}

	public List<Instituicao> getFiltroInstituicao() {
		return filtroInstituicao;
	}

	public void setFiltroInstituicao(List<Instituicao> filtroInstituicao) {
		this.filtroInstituicao = filtroInstituicao;
	}

	public void salvar(Instituicao instituicao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			this.em.persist(instituicao);
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/sigco/auth/comum/listas/listaInstituicao.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Instituicao instituicao) {
		try {
			Instituicao inst = this.em.find(Instituicao.class,
					this.instituicao.getCnpj());
			this.em.remove(inst);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.instituicao = (Instituicao) event.getObject();

		FacesMessage msg = new FacesMessage("Instituição Editada",
				instituicao.getRazaoSocial());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(instituicao);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Instituição Cancelada",
				((Instituicao) event.getObject()).getRazaoSocial());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Instituição Removido",
				instituicao.getRazaoSocial());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.instituicao.getCnpj() != null) {
			return "Editando Instituição";
		} else {
			return "Cadastrando Instituição";
		}
	}

}
