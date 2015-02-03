package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.SubareaAtuacao;

@ManagedBean
@RequestScoped
public class SubareaConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		SubareaAtuacao subarea = (SubareaAtuacao) object;
		if (subarea == null || subarea.getDescricao() == null)
			return null;
		return String.valueOf(subarea.getDescricao());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		SubareaAtuacao subarea = em.find(SubareaAtuacao.class, id);
		System.out.println("TESTE "+subarea.getArea().getDescricao());
		return subarea;
	}
}
