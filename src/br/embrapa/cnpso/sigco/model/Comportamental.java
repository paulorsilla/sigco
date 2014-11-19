package br.embrapa.cnpso.sigco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Comportamental extends Competencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	private String origem;

	private boolean validacao;

	private int grau;

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public boolean isValidacao() {
		return validacao;
	}

	public void setValidacao(boolean validacao) {
		this.validacao = validacao;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + grau;
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result + (validacao ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comportamental other = (Comportamental) obj;
		if (grau != other.grau)
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (validacao != other.validacao)
			return false;
		return true;
	}

}
