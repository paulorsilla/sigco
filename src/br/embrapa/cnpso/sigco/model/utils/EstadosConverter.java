package br.embrapa.cnpso.sigco.model.utils;

import javax.ejb.Stateful;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Estados;

@Named
@Stateful
@FacesConverter(forClass = Estados.class)
public class EstadosConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Estados estados = (Estados) object;
		if (estados == null || estados.getUf() == null)
			return null;
		return String.valueOf(estados.getUf());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Estados estados = em.find(Estados.class, string);
		return estados;
	}

}