package br.com.controlmore.vh;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Parcela;
import br.com.controlmore.dominio.Pessoa;

public class CategoriaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Categoria categoria = new Categoria();
		Categoria cat = new Categoria();
		
		if(!acao.equals("visualizar")){
			int id = 0;
			String descricao = null;
			String cor = null;
			String descrSub = null;
			LocalDate data = LocalDate.now();

			//copiando as informações do formulário web
			if(request.getParameter("txtId")!=null && !request.getParameter("txtId").trim().equals("") ){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			if(request.getParameter("txtDescricao")!=null){
				descricao = request.getParameter("txtDescricao");
			}
			if(request.getParameter("txtData")!= null){
				data = LocalDate.parse(request.getParameter("txtData"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				categoria.setDtCadastro(Date.from(instant));
			}
			else{
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				categoria.setDtCadastro(Date.from(instant));
			}
			if(request.getParameter("txtCor")!=null){
				descricao = request.getParameter("txtCor");
			}
			if(request.getParameter("txtDescrSub")!=null){
				descrSub = request.getParameter("txtDescrSub");
			}
			//atribuindo
			categoria.setId(id);
			categoria.setDescricao(descricao);
			categoria.setCor(cor);
			cat.setDescricao(descrSub);
			categoria.setCategoria(cat);
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("categoria");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					categoria = (Categoria)e;
				}
			}
		}

		return categoria;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		
			if(acao.equals("salvar")){
				request.getSession().setAttribute("resultado",null);
				request.getSession().setAttribute("resultado", resultado);
				request.setAttribute("categoria", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				request.setAttribute("categoria", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");
			}
			if (acao.equals("excluir")){
				request.setAttribute("categoria", null);
				d = request.getRequestDispatcher("/Home?acao=resumo");
			}
			if(acao.equals("consultar")){
				List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
				for (EntidadeDominio entidadeDominio : resultado.getEntidades()) {
					categorias.add(entidadeDominio);
				}
				if(categorias.size()==1){
					request.setAttribute("categoria", categorias.get(0));
					d = request.getRequestDispatcher("/cadastroCategoria.jsp");
				}else{
					request.setAttribute("categoria", categorias);
					d = request.getRequestDispatcher("/listaCategorias.jsp");
				}
			}
			if(acao.equals("visualizar")){
				request.setAttribute("categoria", resultado);
				d = request.getRequestDispatcher("/cadastroCategoria.jsp");
			}
		
		d.forward(request, response);
	}

}
