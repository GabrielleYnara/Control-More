package br.com.controlmore.negocio;

import br.com.controlmore.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
