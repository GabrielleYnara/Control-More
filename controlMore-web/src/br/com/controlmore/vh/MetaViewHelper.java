package br.com.controlmore.vh;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Meta;

public class MetaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Meta meta = new Meta();
		
		if(!acao.equals("visualizar")){
			int id = 0;
			String objetivo = null;
			float valorMensal = 0;
			float valorTotal = 0;
			LocalDate data = LocalDate.now();
			float saldo = 0;
			
			if(request.getParameter("txtId")!=null){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			if(request.getParameter("txtPrazo")!= null && !request.getParameter("txtPrazo").trim().equals("")){
				data = LocalDate.parse(request.getParameter("txtPrazo"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				meta.setPrazo(Date.from(instant));
			}
			else{
				meta.setPrazo(null);
			}
			if(request.getParameter("txtValorTotal")!=null){
				valorTotal = Float.parseFloat(request.getParameter("txtValorTotal"));
			}
			if(request.getParameter("txtValorMensal")!=null){
				valorMensal = Float.parseFloat(request.getParameter("txtValorMensal"));
			}
			if(request.getParameter("txtObjetivo")!=null){
				objetivo = request.getParameter("txtObjetivo");
			}
			if(request.getParameter("txtSaldo")!=null){
				saldo = Float.parseFloat(request.getParameter("txtSaldo"));
			}
			
			if(id != 0){
				meta.setId(id);
			}
			if(objetivo != null && !objetivo.trim().equals("")){
				meta.setObjetivo(objetivo);
			}
			if(valorMensal!=0){
				meta.setValorMensal(valorMensal);
			}
			if(valorTotal!=0){
				meta.setValorTotal(valorTotal);
			}
			if(saldo!=0){
				meta.setSaldo(saldo);
			}
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("meta");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					meta = (Meta)e;
				}
			}
		}
		return meta;

	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				resultado.setMsg("Meta cadastrada com sucesso!");
				request.getSession().setAttribute("meta", resultado);
				d = request.getRequestDispatcher("/principal.jsp");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				request.getSession().setAttribute("meta", resultado);
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if (acao.equals("excluir")){
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if(acao.equals("consultar")){
				Meta meta = new Meta();
				meta = (Meta) resultado.getEntidades().get(0);
				if(meta.getMsg()!=null)
					request.getSession().setAttribute("metaSimulada", resultado);
				else
					request.getSession().setAttribute("meta", resultado);
				d = request.getRequestDispatcher("/metas.jsp");
			}
			if(acao.equals("visualizar")){
				request.getSession().setAttribute("meta", resultado);
				d = request.getRequestDispatcher("/alterarMeta.jsp");
			}
		}
		else{
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("/metas.jsp");//redireciona a pagina
		}
		d.forward(request, response);

	}

}
