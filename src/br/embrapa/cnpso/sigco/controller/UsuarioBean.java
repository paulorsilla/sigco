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

import org.springframework.security.core.context.SecurityContextHolder;

import br.embrapa.cnpso.sigco.model.Autorizacao;
import br.embrapa.cnpso.sigco.model.Usuario;

@Stateful
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Usuario usuario;
	private Autorizacao autorizacao;
	private Collection<Usuario> listaUsuarios;
	private List<Usuario> filtroUsuarios;

	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		this.usuario = new Usuario();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Usuario.class));

		listaUsuarios = em.createQuery(cQ).getResultList();

	}

	public Collection<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Autorizacao getAutorizacao() {
		return this.autorizacao;
	}

	public void setUsuario(Usuario usuario) {
		System.out.println("Passei por aqui.");
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public List<Usuario> getFiltroUsuarios() {
		return this.filtroUsuarios;
	}

	public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public String validaAuth() {
		String login = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		return login;
	}

	public void salvar(Usuario usr) {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String id = UIComponent.getCurrentComponent(context).getId();
			System.out.println(id);

			if (this.usuario.getLogin() != null) {
				this.em.merge(usr);
			} else {
				this.em.persist(usr);
			}
			this.em.flush();
			if (id.equals("salvarfechar")) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/sigco/auth/comum/listas/listaUsuarios.jsf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Usuario usr) {

		try {
			Usuario usuario = this.em.find(Usuario.class, usr.getLogin());
			this.em.remove(usuario);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Usuário Removido",
				usuario.getNomeCompleto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String Editando() {
		if (this.usuario.getLogin() != null) {
			return "Editando Usuário";
		} else {
			return "Cadastrando Usuário";
		}
	}
}
