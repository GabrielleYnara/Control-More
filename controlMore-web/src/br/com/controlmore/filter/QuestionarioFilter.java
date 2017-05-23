package br.com.controlmore.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dao.PessoaDAO;
import br.com.controlmore.dao.QuestionarioDAO;
import br.com.controlmore.dominio.Pessoa;
import br.com.controlmore.dominio.Questionario;

/**
 * Servlet Filter implementation class QuestionarioFilter
 */
@WebFilter("/Questionario")
public class QuestionarioFilter implements Filter {

    /**
     * Default constructor. 
     */
    public QuestionarioFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; //Casting para que request possa acessar os metodos do HttpServlet
		
		String acao = httpRequest.getParameter("acao");
		Resultado result = (Resultado) httpRequest.getSession().getAttribute("pessoa");
		Pessoa pessoa = (Pessoa) result.getEntidades().get(0);
		if(acao.equals("salvar")){
			chain.doFilter(request, response);
			PessoaDAO pDAO = new PessoaDAO();
			QuestionarioDAO qDAO = new QuestionarioDAO();
			result = (Resultado) httpRequest.getSession().getAttribute("questionario");
			Questionario questionario = (Questionario) result.getEntidades().get(0);
			questionario.setId(qDAO.consultar(pessoa).get(0).getId());
			pessoa.setQuestionario(questionario);
			pDAO.alterar(pessoa);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
