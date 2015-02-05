package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Funcao;

@ManagedBean
@RequestScoped
public class FuncaoConverter implements Converter {

		@PersistenceContext
		private EntityManager em;

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object object) {
			Funcao funcao = (Funcao) object;
			if (funcao == null || funcao.getDescricao() == null)
				return null;
			return String.valueOf(funcao.getId());
		}

		@Override
		public Object getAsObject(FacesContext context, UIComponent component,
				String string) {
			if (string == null || string.isEmpty())
				return null;
			Long id = new Long(string);
			Funcao funcao = em.find(Funcao.class, id);
			return funcao;
		}
}
