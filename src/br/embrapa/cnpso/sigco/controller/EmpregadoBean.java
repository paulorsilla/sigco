package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.primefaces.event.RowEditEvent;

import br.embrapa.cnpso.sigco.model.Empregado;

@ViewScoped
@Stateful
@Named
public class EmpregadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Empregado empregado;
	private Collection<Empregado> listaEmpregado;
	private List<Empregado> filtroEmpregado;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Init() {
		this.empregado = new Empregado();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Empregado.class));

		listaEmpregado = em.createQuery(cQ).getResultList();
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Collection<Empregado> getListaEmpregado() {
		return listaEmpregado;
	}

	public void setListaEmpregado(Collection<Empregado> listaEmpregado) {
		this.listaEmpregado = listaEmpregado;
	}

	public List<Empregado> getFiltroEmpregado() {
		return filtroEmpregado;
	}

	public void setFiltroEmpregado(List<Empregado> filtroEmpregado) {
		this.filtroEmpregado = filtroEmpregado;
	}

	public void salvar(Empregado empregado) {
		try {
			this.em.persist(empregado);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.Init();
		}
	}

	public void excluir(Empregado empregado) {
		try {
			Empregado emprega = this.em.find(Empregado.class,
					this.empregado.getMatricula());
			this.em.remove(emprega);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.Init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.empregado = (Empregado) event.getObject();

		FacesMessage msg = new FacesMessage("Empregado Editado",
				empregado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(empregado);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada",
				((Empregado) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Empregado Removido",
				empregado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
