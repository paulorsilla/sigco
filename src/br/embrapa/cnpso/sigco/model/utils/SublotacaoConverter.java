package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Sublotacao;

@ManagedBean
@RequestScoped
public class SublotacaoConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Sublotacao sublotacao = (Sublotacao) object;
		if (sublotacao == null || sublotacao.getDescricao() == null)
			return null;
		return String.valueOf(sublotacao.getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		Sublotacao sublotacao = em.find(Sublotacao.class, id);
		return sublotacao;
	}
}
