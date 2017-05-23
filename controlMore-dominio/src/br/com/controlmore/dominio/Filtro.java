package br.com.controlmore.dominio;

import java.util.Date;

public class Filtro extends EntidadeDominio{
	private Date DtInicio;
	private Date DtFinal;
	private String consulta;
	public Date getDtInicio() {
		return DtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		DtInicio = dtInicio;
	}
	public Date getDtFinal() {
		return DtFinal;
	}
	public void setDtFinal(Date dtFinal) {
		DtFinal = dtFinal;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	
}
	