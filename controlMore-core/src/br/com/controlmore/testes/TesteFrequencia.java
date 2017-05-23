package br.com.controlmore.testes;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dao.FrequenciaDAO;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Frequencia;

public class TesteFrequencia {

	public static void main(String[] args) {

		salvarFrequencia();
		//alterarDesc();
		//alterarDias();
		//excluirFrequencia();
		//consultar();
	}

	public static void consultar(){
		Frequencia f = new Frequencia();
		FrequenciaDAO fDAO = new FrequenciaDAO();
		List<EntidadeDominio> frequencias = new ArrayList<EntidadeDominio>();
		frequencias = fDAO.consultar(f);
		StringBuilder st = new StringBuilder();
		for (EntidadeDominio entidade : frequencias) {
			Frequencia freq = (Frequencia) entidade; //Casting de EntidadeDominio para Frequencia, para que freq tenha acesso aos dados de Frequencia 
			st.append(freq.getId());
			st.append(" - ");
			st.append(freq.getDescricao());
			st.append(" - ");
			st.append(freq.getQtdeDias());
			st.append("\n");
		}
		System.out.println(st);
	}
	public static void excluirFrequencia(){
		Frequencia f = new Frequencia();
		f.setId(4);
		FrequenciaDAO fDAO = new FrequenciaDAO();
		System.out.println(fDAO.excluir(f));
	}
	public static void alterarDias(){
		Frequencia f = new Frequencia();
		//f.setId(4);
		f.setQtdeDias(9);
		FrequenciaDAO fDAO = new FrequenciaDAO();
		System.out.println(fDAO.alterar(f));
	}
	
	public static void alterarDesc(){
		Frequencia f = new Frequencia();
		f.setId(4);
		f.setDescricao("testeAlterar");
		FrequenciaDAO fDAO = new FrequenciaDAO();
		System.out.println(fDAO.alterar(f));
	}
	public static void salvarFrequencia(){
		Frequencia f = new Frequencia();
		f.setId(6);
		f.setDescricao("teste");
		f.setQtdeDias(1);
		FrequenciaDAO fDAO = new FrequenciaDAO();
		
		String result = fDAO.salvar(f);
		if(result!= null){
			System.out.println(result);
			System.out.println("entrou");
		}else{
			System.out.println("Ok");
		}
		
	}
}
