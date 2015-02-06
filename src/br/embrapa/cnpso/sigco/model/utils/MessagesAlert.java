package br.embrapa.cnpso.sigco.model.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesAlert {

	public void save() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Registro cadastrado com sucesso", null));
	}

	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
						"Watch out for PrimeFaces."));
	}

	public void error() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Contact admin."));
	}

	public void fatal() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!",
						"System Error"));
	}

}
