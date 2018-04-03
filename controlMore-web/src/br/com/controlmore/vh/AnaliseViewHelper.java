package br.com.controlmore.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class AnaliseViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		String acao = request.getParameter("acao");
		if(acao.equals("analisar")){
			request.getSession().setAttribute("resultado", null);
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("/analisarAtraso.jsp");//redireciona a pagina
		}
		
		d.forward(request, response);
		
	}

}
