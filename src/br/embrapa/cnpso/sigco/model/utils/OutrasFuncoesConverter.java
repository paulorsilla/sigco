package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.OutrasFuncoes;

@ManagedBean
@RequestScoped
public class OutrasFuncoesConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		OutrasFuncoes outrasFuncoes = (OutrasFuncoes) object;
		if (outrasFuncoes == null || outrasFuncoes.getDescricao() == null)
			return null;
		return String.valueOf(outrasFuncoes.getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		OutrasFuncoes outrasFuncoes = em.find(OutrasFuncoes.class, id);
		return outrasFuncoes;
	}
}
