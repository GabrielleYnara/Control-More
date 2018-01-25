package br.com.controlmore.vm;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Conta;
import br.com.controlmore.viewmodel.IViewModel;

public class aReceberVM implements IViewModel{
	//Sera usada para receber as contas cadastradas no banco
	private List<Conta> contas = new ArrayList<Conta>();

	public List<Conta> getContas() {
		return contas;
	}

	public void setConta(Conta conta) {
		this.contas.add(conta);
	}
	
	

}
