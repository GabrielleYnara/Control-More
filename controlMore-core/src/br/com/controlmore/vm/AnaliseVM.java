package br.com.controlmore.vm;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Saida;
import br.com.controlmore.viewmodel.IViewModel;

public class AnaliseVM implements IViewModel{
	private List<Saida> aPagar = new ArrayList<Saida>();
	
	public void setaPagar(Saida s) {
		this.aPagar.add(s);
	}
	public List<Saida> getaPagar(){
		return aPagar;
	}

}
