package br.embrapa.cnpso.sigco.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empregado extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	private String matricula;

	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;

	@Temporal(TemporalType.DATE)
	private Date dataDesligamento;

	private boolean situacao;

	@OneToMany
	private Collection<Competencia> competencia;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Collection<Competencia> getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Collection<Competencia> competencia) {
		this.competencia = competencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((competencia == null) ? 0 : competencia.hashCode());
		result = prime * result
				+ ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime
				* result
				+ ((dataDesligamento == null) ? 0 : dataDesligamento.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + (situacao ? 1231 : 1237);
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
		Empregado other = (Empregado) obj;
		if (competencia == null) {
			if (other.competencia != null)
				return false;
		} else if (!competencia.equals(other.competencia))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (dataDesligamento == null) {
			if (other.dataDesligamento != null)
				return false;
		} else if (!dataDesligamento.equals(other.dataDesligamento))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}

}
