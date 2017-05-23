package br.com.controlmore.command;


import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class ConsultarCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		//Retorna um objeto de fachada que chama o metodo consultar da entidade que foi passada
		return (Resultado) fachada.consultar(entidade);
	}

}
