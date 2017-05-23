package br.com.controlmore.command;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class AlterarCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		//Retorna um objeto de fachada que chama o metodo alterar da entidade que foi passada
		return fachada.alterar(entidade);
	}

}
