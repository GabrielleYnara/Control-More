package br.com.controlmore.testes;


import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dao.PessoaDAO;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Pessoa;
import br.com.controlmore.dominio.Usuario;
import br.com.controlmore.negocio.CompletarDtCadastro;

public class TestePessoa {
	public static void main(String[] args) {

		salvar();
		//alterar();
		//excluir();
		//consultar();
	}
	public static void salvar(){
		Pessoa p = new Pessoa();
		Usuario u = new Usuario();
		PessoaDAO pDAO = new PessoaDAO();
		
		p.setId(1);
		//p.setDtNascimento("21/11/1994");
		p.setNome("Gabrielle Ynara");
		p.setTelefone("1234");

		u.setEmail("gabi@emial.com");
		u.setSenha("123");
		
		p.setUsuario(u);
		
		CompletarDtCadastro c = new CompletarDtCadastro();
		c.processar(p);
		
		String result = pDAO.salvar(p);
		if(result !=null){
			System.out.println(result);
			System.out.println("deu merda");
		}else{
			System.out.println("Tudo Ok");
		}
	}
	
	public static void alterar(){
		Pessoa p = new Pessoa();
		//Usuario u = new Usuario();
		PessoaDAO pDAO = new PessoaDAO();
		
		p.setId(1);
		//p.setDtNascimento("21/11/1994");
		//p.setNome("Gabrielle Ynara");
		p.setTelefone("1234-1234");

		//u.setEmail("gabi@emial.com");
		//u.setSenha("123");
		
		//p.setUsuario(u);
		
		String result = pDAO.alterar(p);
		if(result !=null){
			System.out.println(result);
		}else{
			System.out.println("Tudo Ok");
		}
	}
	
	public static void excluir(){
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		
		p.setId(1);
		
		String result = pDAO.excluir(p);
		if(result !=null){
			System.out.println(result);
		}else{
			System.out.println("Tudo Ok");
		}
	}
	
	public static void consultar(){
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
		p.setId(1);
		pessoas = pDAO.consultar(p);
		StringBuilder st = new StringBuilder();
		for (EntidadeDominio entidade : pessoas) {
			Pessoa pessoa = (Pessoa) entidade; //Casting de EntidadeDominio para Pessoa, para que freq tenha acesso aos dados de Pessoa 
			st.append(pessoa.getId());
			st.append(pessoa.getNome());
			st.append("\n");
			st.append(pessoa.getDtNascimento());
			st.append("\n");
			st.append(pessoa.getTelefone());
			st.append("\n");
			st.append(pessoa.getUsuario().getEmail());
			st.append("\n");
			st.append(pessoa.getUsuario().getSenha());
		}
		if(st!=null){
			System.out.print(st);
		}else{
			System.out.println("deu merda");
		}
	}
}
