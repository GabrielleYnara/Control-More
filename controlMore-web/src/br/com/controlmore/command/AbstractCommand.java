package br.com.controlmore.command;

import br.com.controlmore.controle.Fachada;
import br.com.controlmore.controle.IFachada;

public abstract class AbstractCommand implements ICommand {

	//Instancia um objeto de fachada, pois a fachada que fará "contato" com a Command
	protected IFachada fachada = new Fachada();

}
