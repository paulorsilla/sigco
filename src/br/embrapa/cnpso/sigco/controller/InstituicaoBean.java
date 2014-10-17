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

import br.embrapa.cnpso.sigco.model.Instituicao;

@Named
@Stateful
@ViewScoped
public class InstituicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Instituicao instituicao;
	private Collection<Instituicao> listaInstituicao;
	private List<Instituicao> filtroInstituicao;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.instituicao = new Instituicao();

		Query query = this.em.createQuery(
				"SELECT i FROM Instituicao i ORDER BY i.razaoSocial",
				Instituicao.class);
		this.listaInstituicao = query.getResultList();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
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
			this.em.persist(instituicao);
			this.em.flush();
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

		FacesMessage msg = new FacesMessage("Instituição Editado",
				instituicao.getRazaoSocial());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(instituicao.getCnpj());
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

}
