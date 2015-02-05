package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Facilitador;

@ManagedBean
@RequestScoped
public class FacilitadorConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Facilitador facilitador = (Facilitador) object;
		if (facilitador == null || facilitador.getNome() == null)
			return null;
		return String.valueOf(facilitador.getNome());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String cpf) {
		if (cpf == null || cpf.isEmpty())
			return null;
		Facilitador facilitador = em.find(Facilitador.class, cpf);
		return facilitador;
	}
}
