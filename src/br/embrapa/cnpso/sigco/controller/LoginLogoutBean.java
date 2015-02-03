package br.embrapa.cnpso.sigco.controller;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginLogoutBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public LoginLogoutBean() {

	}

	public String logout() {
		return "/j_spring_security_logout";
	}
}
