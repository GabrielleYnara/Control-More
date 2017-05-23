package br.com.controlmore.command;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		
		//retorna um objeto de fachada, chamando o método salvar da entidade que foi passada
		return fachada.salvar(entidade);
	}

}
