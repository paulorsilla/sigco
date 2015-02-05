package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.embrapa.cnpso.sigco.model.Cargo;

@ManagedBean
@RequestScoped
public class CargoConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = new Long(string);
		Cargo cargo = em.find(Cargo.class, id);
		return cargo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Cargo cargo = (Cargo) object;
		if (cargo == null || cargo.getDescricao() == null)
			return null;
		return String.valueOf(cargo.getId());
	}
}
