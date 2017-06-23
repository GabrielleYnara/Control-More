package br.com.controlmore.negocio;

import java.util.Date;

import br.com.controlmore.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);

	public String processar(Date dtInicio, Date dtFinal);
}
