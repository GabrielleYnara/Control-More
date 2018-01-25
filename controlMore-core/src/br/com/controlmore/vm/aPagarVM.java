package br.com.controlmore.vm;

import br.com.controlmore.viewmodel.IViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;


public class aPagarVM implements IViewModel{
	//Sera usada para receber as contas e cartoes cadastradas no banco e exibir na tela de conta a Pagar
	//Vou usar uma lista de conta para armazenar as informões que vão vir do banco
	//Vai ser um select que traz id, tipo e banco de conta, id, bandeir e banco de cartao 
	//Conta.id vai receber Id de ambos
	//Conta.tipo vai receber tipo de conta e bandeira de cartao
	//Conta.banco vai receber banco de ambos
	//Conta.info vai receber "conta" ou "cartão" para identificar o tipo de objeto que deve ser usado
	private List<Conta> contasCartoes = new ArrayList<Conta>();
	
	//Sera usada para receber as categorias cadastradas no banco e exibir na tela de conta a Pagar
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	public List<Conta> getContasCartoes() {
		return contasCartoes;
	}

	public void setContasCartoes(Conta contasCartoes) {
		this.contasCartoes.add(contasCartoes);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategoria(Categoria categorias) {
		this.categorias.add(categorias);
	}
}
