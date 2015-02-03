package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioConverter implements Converter {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Usuario usuario = em.find(Usuario.class, string);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getLogin() == null ? null : usuario.getLogin();
		}
 		return "";
	}
}
