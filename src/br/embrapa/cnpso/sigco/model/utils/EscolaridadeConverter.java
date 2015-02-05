package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Escolaridade;

@ManagedBean
@RequestScoped
public class EscolaridadeConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Escolaridade escolaridade = (Escolaridade) object;
		if (escolaridade == null || escolaridade.getDescricao() == null)
			return null;
		return String.valueOf(escolaridade.getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		Escolaridade escolaridade = em.find(Escolaridade.class, id);
		return escolaridade;
	}

}
