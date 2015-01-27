package br.embrapa.cnpso.sigco.model.utils.mail;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SessionConfig config;
	
	public MailMessage novaMensagem() {
		return new MailMessageImpl(this.config);
	}

}
