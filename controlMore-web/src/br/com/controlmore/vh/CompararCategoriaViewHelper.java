package br.com.controlmore.vh;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.RelatorioCategoria;
import br.com.controlmore.vm.compararCategoriaVM;

public class CompararCategoriaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		compararCategoriaVM compararVM = new compararCategoriaVM();
		LocalDate data = LocalDate.now();
		int idCategoria1 = 0;
		int idCategoria2 = 0;
		if(request.getParameter("txtDataInicial")!= null){
			data = LocalDate.parse(request.getParameter("txtDataInicial"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			compararVM.setDtInicial(Date.from(instant));
		}else{// atribui primeiro dia do mês
			data = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			compararVM.setDtInicial(Date.from(instant));
		}
		if(request.getParameter("txtDataFinal")!= null){
			data = LocalDate.parse(request.getParameter("txtDataFinal"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			compararVM.setDtFinal(Date.from(instant));
		}else{// atribui ultimo dia do mês
			data = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			compararVM.setDtFinal(Date.from(instant));
		}
		if(request.getParameter("categoria1")!=null){
			idCategoria1 = Integer.parseInt(request.getParameter("categoria1"));
		}
		if(request.getParameter("categoria2")!=null){
			idCategoria2 = Integer.parseInt(request.getParameter("categoria2"));
		}
	
		
		compararVM.setIdCategoria1(idCategoria1);
		compararVM.setIdCategoria2(idCategoria2);
		Resultado r = new Resultado();
		r.setModeloVisao(compararVM);
		return r;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(acao.equals("comparar")){
			request.getSession().setAttribute("resultado", null);
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("/comparacaoCategoria.jsp");//redireciona a pagina
		}
		
		d.forward(request, response);
		
	}

}
