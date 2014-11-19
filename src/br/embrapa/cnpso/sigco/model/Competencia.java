package br.embrapa.cnpso.sigco.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Competencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(length = 20)
	private String competencia;

	@OneToMany
	private Collection<Empregado> empregado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public Collection<Empregado> getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Collection<Empregado> empregado) {
		this.empregado = empregado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((competencia == null) ? 0 : competencia.hashCode());
		result = prime * result
				+ ((empregado == null) ? 0 : empregado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competencia other = (Competencia) obj;
		if (competencia == null) {
			if (other.competencia != null)
				return false;
		} else if (!competencia.equals(other.competencia))
			return false;
		if (empregado == null) {
			if (other.empregado != null)
				return false;
		} else if (!empregado.equals(other.empregado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
