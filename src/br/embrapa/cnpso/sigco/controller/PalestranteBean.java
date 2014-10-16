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

import br.embrapa.cnpso.sigco.model.Palestrante;

@Named
@Stateful
@ViewScoped
public class PalestranteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Palestrante palestrante;
	private Collection<Palestrante> listaPalestrante;
	private List<Palestrante> filtroPalestrante;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.palestrante = new Palestrante();

		Query query = this.em.createQuery(
				"SELECT pa FROM Palestrante ORDER BY pa.nome",
				Palestrante.class);

		this.listaPalestrante = query.getResultList();
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public Collection<Palestrante> getListaPalestrante() {
		return listaPalestrante;
	}

	public void setListaPalestrante(Collection<Palestrante> listaPalestrante) {
		this.listaPalestrante = listaPalestrante;
	}

	public List<Palestrante> getFiltroPalestrante() {
		return filtroPalestrante;
	}

	public void setFiltroPalestrante(List<Palestrante> filtroPalestrante) {
		this.filtroPalestrante = filtroPalestrante;
	}

	public void salvar(Palestrante palestrante) {
		try {
			this.em.persist(palestrante);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Palestrante palestrante) {
		try {
			Palestrante palestra = this.em.find(Palestrante.class,
					this.palestrante.getCpf());
			this.em.remove(palestra);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		this.palestrante = (Palestrante) event.getObject();

		FacesMessage msg = new FacesMessage("Palestrante Editado",
				palestrante.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			this.em.merge(palestrante.getCpf());
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Palestrate Cancelado",
				((Palestrante) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
