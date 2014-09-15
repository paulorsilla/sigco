package br.embrapa.cnpso.sigco.model.utils;

import javax.ejb.Stateful;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Autorizacao;

@Named
@Stateful
@ViewScoped
@FacesConverter(forClass = Autorizacao.class)
public class AutorizacaoConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Autorizacao autorizacao = (Autorizacao) object;
		if (autorizacao == null || autorizacao.getNome() == null)
			return null;
		return String.valueOf(autorizacao.getNome());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Autorizacao autorizacao = em.find(Autorizacao.class, string);
		return autorizacao;
	}

}