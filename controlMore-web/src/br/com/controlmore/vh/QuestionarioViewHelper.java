package br.com.controlmore.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Questionario;

public class QuestionarioViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Questionario questionario = null;
		
		if(!acao.equals("visualizar")){
			float renda = 0;
			float compra = 0;
			float agua = 0;
			float luz = 0;
			float internet = 0;
			float recarga = 0;
			float transporte = 0;
			float aluguel = 0;
			float educacao = 0;
			float lazer = 0;
			float outros = 0;
			int id =0;
			questionario = new Questionario();
			
			if(request.getParameter("txtRenda")!=null){
				renda = (float) Double.parseDouble(request.getParameter("txtRenda"));
			}
			if(request.getParameter("txtCompra")!=null){
				compra = (float) Double.parseDouble(request.getParameter("txtCompra"));
			}
			if(request.getParameter("txtAgua")!=null){
				agua = (float) Double.parseDouble(request.getParameter("txtAgua"));
			}
			if(request.getParameter("txtLuz")!=null){
				luz = (float) Double.parseDouble(request.getParameter("txtLuz"));
			}
			if(request.getParameter("txtInternet")!=null){
				internet = (float) Double.parseDouble(request.getParameter("txtInternet"));
			}
			if(request.getParameter("txtRecarga")!=null){
				recarga = (float) Double.parseDouble(request.getParameter("txtRecarga"));
			}
			if(request.getParameter("txtTransporte")!=null){
				transporte = (float) Double.parseDouble(request.getParameter("txtTransporte"));
			}
			if(request.getParameter("txtAluguel")!=null){
				aluguel = (float) Double.parseDouble(request.getParameter("txtAluguel"));
			}
			if(request.getParameter("txtEducacao")!=null){
				educacao = (float) Double.parseDouble(request.getParameter("txtEducacao"));
			}
			if(request.getParameter("txtLazer")!=null){
				lazer = (float) Double.parseDouble(request.getParameter("txtLazer"));
			}
			if(request.getParameter("txtOutros")!=null){
				outros = (float) Double.parseDouble(request.getParameter("txtOutros"));
			}
			if(request.getParameter("txtId")!=null && !request.getParameter("txtId").trim().equals("")){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			questionario.setAgua(agua);
			questionario.setAluguel(aluguel);
			questionario.setCompra(compra);
			questionario.setEducacao(educacao);
			questionario.setInternet(internet);
			questionario.setLazer(lazer);
			questionario.setLuz(luz);
			questionario.setOutros(outros);
			questionario.setRecarga(recarga);
			questionario.setRenda(renda);
			questionario.setTransporte(transporte);
			questionario.setId(id);
			
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("questionario");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					questionario = (Questionario)e;
				}
			}
		}
		return questionario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				resultado.setMsg("Obrigado por colaborar :)");
				request.getSession().setAttribute("resultado", resultado);
				request.getSession().setAttribute("questionario", resultado);
				d = request.getRequestDispatcher("/principal.jsp");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if (acao.equals("excluir")){
				
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if(acao.equals("consultar")){
				
			}
			if(acao.equals("visualizar")){
				
			}
		}else{
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("/principal.jsp");
		}
		d.forward(request, response);
	}
}
