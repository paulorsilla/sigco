package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.embrapa.cnpso.sigco.model.utils.mail.Mailer;

@Named
@RequestScoped
public class EnviaEmailBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	public void enviar() {
		MailMessage message = mailer.novaMensagem();
		message.to("paulo.silla@embrapa.br").subject("Seu teste de e-mail").bodyHtml("Testando o envio de e-mail com o Java!").send();
	}
}
