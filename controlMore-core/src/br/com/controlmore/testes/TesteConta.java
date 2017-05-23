package br.com.controlmore.testes;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dao.ContaDAO;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;

public class TesteConta {

	public static void main(String[] args) {
		//salvar();
		//alterar();
		consultar();
	}
	
	public static void salvar(){
		Conta c = new Conta();
		ContaDAO cDAO = new ContaDAO();
		c.setId(2);
		c.setBanco("Itau");
		c.setTipo("corrente");
		System.out.println(cDAO.salvar(c));
	}
	public static void alterar(){
		Conta c = new Conta();
		ContaDAO cDAO = new ContaDAO();
		c.setId(1);
		c.setTipo("Carteira");
		c.setBanco("Carteira");
		c.setInfo("");
		System.out.println(cDAO.alterar(c));
	}
	public static void consultar(){
		Conta c = new Conta();
		ContaDAO cDAO = new ContaDAO();
		List<EntidadeDominio> contas = new ArrayList<EntidadeDominio>();
		contas = cDAO.consultar(c);
		StringBuilder st = new StringBuilder();
		for(EntidadeDominio entidade: contas)
		{
			Conta conta = (Conta) entidade; // Casting de entidade para conta
			st.append(conta.getId());
			st.append(" - ");
			st.append(conta.getBanco());
			st.append(", tipo: ");
			st.append(conta.getTipo());
			st.append(" R$");
			st.append("\nInfo: ");
			st.append(conta.getInfo());
		}
		System.out.println(st);
	}

}
