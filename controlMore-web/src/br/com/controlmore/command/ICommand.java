package br.com.controlmore.command;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public interface ICommand {

	//Faz com que as classes que irão implementar a ICommand tenham um método execute que retorna uma String;
	public Resultado execute(EntidadeDominio entidade);
		
}