package br.com.controlmore.command;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class AnalisarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.analisar();
	}

}
