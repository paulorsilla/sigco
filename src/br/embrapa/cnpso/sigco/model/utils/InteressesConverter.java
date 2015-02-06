package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Interesses;

@ManagedBean
@RequestScoped
public class InteressesConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Interesses interesses = (Interesses) object;
		if (interesses == null || interesses.getDescricao() == null)
			return null;
		return String.valueOf(interesses.getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Interesses interesses = em.find(Interesses.class,
				Long.parseLong(string));
		return interesses;
	}
}
