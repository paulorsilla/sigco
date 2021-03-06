package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.AreaAtuacao;

@ManagedBean
@RequestScoped
public class AreaConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		AreaAtuacao area = (AreaAtuacao) object;
		if (area == null || area.getDescricao() == null)
			return null;
		return String.valueOf(area.getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		AreaAtuacao area = em.find(AreaAtuacao.class, Long.parseLong(string));
		return area;
	}

}
