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

import br.embrapa.cnpso.sigco.model.AreaAtuacao;
import br.embrapa.cnpso.sigco.model.SubareaAtuacao;
import br.embrapa.cnpso.sigco.model.utils.MessagesAlert;

@Named
@Stateful
@ViewScoped
public class AreaAtuacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private AreaAtuacao area;
	private SubareaAtuacao subarea;
	private Collection<AreaAtuacao> listaArea;
	private List<AreaAtuacao> filtroArea;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.area = new AreaAtuacao();
		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(AreaAtuacao.class));

		listaArea = em.createQuery(cQ).getResultList();
	}

	public AreaAtuacao getArea() {
		return area;
	}

	public void setArea(AreaAtuacao area) {
		this.area = area;
	}

	public SubareaAtuacao getSubarea() {
		return subarea;
	}

	public void setSubarea(SubareaAtuacao subarea) {
		this.subarea = subarea;

		if (this.subarea != null) {
			this.area = this.subarea.getArea();
		}
	}

	public Collection<AreaAtuacao> getListaArea() {
		return listaArea;
	}

	public void setListaArea(Collection<AreaAtuacao> listaArea) {
		this.listaArea = listaArea;
	}

	public List<AreaAtuacao> getFiltroArea() {
		return filtroArea;
	}

	public void setFiltroArea(List<AreaAtuacao> filtroArea) {
		this.filtroArea = filtroArea;
	}

	public void salvar(AreaAtuacao area) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.area.getId() != null) {
				this.em.merge(area);
			} else {
				this.em.persist(area);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/auth/comum/listas/listaAreaAtuacao.jsf");
			}
			MessagesAlert alert = new MessagesAlert();
			alert.save();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(AreaAtuacao area) {

		try {
			AreaAtuacao ar = this.em.find(AreaAtuacao.class, area.getId());
			this.em.remove(ar);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Área de Atuação Removido",
				area.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.area.getId() != null) {
			return "Editando Área de Atuação";
		} else {
			return "Cadastrando Área de Atuação";
		}
	}

}