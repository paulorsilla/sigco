package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.EquipeTecnica;

@ManagedBean
@RequestScoped
public class EquipeTecnicaConverter implements Converter {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		EquipeTecnica equipeTecnica = em.find(EquipeTecnica.class, id);
		return equipeTecnica;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		EquipeTecnica equipetecnica = (EquipeTecnica) value;
		if (equipetecnica == null || equipetecnica.getDescricao() == null)
			return null;
		return String.valueOf(equipetecnica.getId());
	}
}
