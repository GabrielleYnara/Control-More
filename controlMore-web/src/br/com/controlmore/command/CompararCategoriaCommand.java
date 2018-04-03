package br.com.controlmore.command;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class CompararCategoriaCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return fachada.comparar(entidade);
	}

}
