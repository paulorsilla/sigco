package br.embrapa.cnpso.sigco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Competencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(length = 20)
	private String competencia;

	private Empregado empregado;
}
