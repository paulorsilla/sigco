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
public class Tecnica extends Competencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 4)
	private int cargaHoraria;

	@Temporal(TemporalType.DATE)
	private Date periodo;

	private boolean acaoCorporativa;

	@OneToMany
	private Collection<TipoCompetenciaTecnica> tipoCompTecnica;

	@OneToMany
	private Collection<Instituicao> instituicao;

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public boolean isAcaoCorporativa() {
		return acaoCorporativa;
	}

	public void setAcaoCorporativa(boolean acaoCorporativa) {
		this.acaoCorporativa = acaoCorporativa;
	}

	public Collection<TipoCompetenciaTecnica> getTipoCompTecnica() {
		return tipoCompTecnica;
	}

	public void setTipoCompTecnica(
			Collection<TipoCompetenciaTecnica> tipoCompTecnica) {
		this.tipoCompTecnica = tipoCompTecnica;
	}

	public Collection<Instituicao> getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Collection<Instituicao> instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (acaoCorporativa ? 1231 : 1237);
		result = prime * result + cargaHoraria;
		result = prime * result
				+ ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result
				+ ((tipoCompTecnica == null) ? 0 : tipoCompTecnica.hashCode());
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
		Tecnica other = (Tecnica) obj;
		if (acaoCorporativa != other.acaoCorporativa)
			return false;
		if (cargaHoraria != other.cargaHoraria)
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (tipoCompTecnica == null) {
			if (other.tipoCompTecnica != null)
				return false;
		} else if (!tipoCompTecnica.equals(other.tipoCompTecnica))
			return false;
		return true;
	}

}
