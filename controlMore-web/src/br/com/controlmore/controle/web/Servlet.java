package br.com.controlmore.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.command.AlterarCommand;
import br.com.controlmore.command.ConsultarCommand;
import br.com.controlmore.command.ContaPagarCommand;
import br.com.controlmore.command.ExcluirCommand;
import br.com.controlmore.command.ICommand;
import br.com.controlmore.command.LoginCommand;
import br.com.controlmore.command.LogoutCommand;
import br.com.controlmore.command.ResumoCommand;
import br.com.controlmore.command.SalvarCommand;
import br.com.controlmore.command.VisualizarCommand;
import br.com.controlmore.command.contaReceberCommand;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.vh.AvaliacaoViewHelper;
import br.com.controlmore.vh.CategoriaViewHelper;
import br.com.controlmore.vh.EntradaViewHelper;
import br.com.controlmore.vh.FiltroViewHelper;
import br.com.controlmore.vh.HomeViewHelper;
import br.com.controlmore.vh.IViewHelper;
import br.com.controlmore.vh.MetaViewHelper;
import br.com.controlmore.vh.PessoaViewHelper;
import br.com.controlmore.vh.QuestionarioViewHelper;
import br.com.controlmore.vh.RelCatViewHelper;
import br.com.controlmore.vh.SaidaViewHelper;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	
	
    public Servlet() {
    	
    	/* Utilizando o command para chamar a fachada e indexando cada command 
    	 * pela operação garantindo que esta servelt atenderá qualquer operação */
    	commands = new HashMap<String, ICommand>();
    	
    	commands.put("salvar", new SalvarCommand());
    	commands.put("alterar", new AlterarCommand());
    	commands.put("excluir", new ExcluirCommand());
    	commands.put("consultar", new ConsultarCommand());
    	commands.put("login", new LoginCommand());
    	commands.put("logout", new LogoutCommand());
    	commands.put("visualizar", new VisualizarCommand());
    	commands.put("resumo", new ResumoCommand());
    	commands.put("contaReceber", new contaReceberCommand());
    	commands.put("contaPagar", new ContaPagarCommand());
 
    	
    	/* Utilizando o ViewHelper para tratar especificações de qualquer tela e indexando 
    	 * cada viewhelper pela url em que esta servlet é chamada no form
    	 * garantimos que esta servelt atenderá qualquer entidade */
    	vhs = new HashMap<String, IViewHelper>();
    	
    	/*A chave do mapa é o mapeamento da servlet para cada form que 
    	 * está configurado no web.xml e sendo utilizada no action do html
    	 */
    	vhs.put("/controlMore-web/Saida", new SaidaViewHelper());
    	vhs.put("/controlMore-web/Pessoa", new PessoaViewHelper());
    	vhs.put("/controlMore-web/Meta", new MetaViewHelper());
    	vhs.put("/controlMore-web/Entrada", new EntradaViewHelper());
    	vhs.put("/controlMore-web/RelCat", new RelCatViewHelper());
    	vhs.put("/controlMore-web/Filtro", new FiltroViewHelper());
    	vhs.put("/controlMore-web/Questionario", new QuestionarioViewHelper());
    	vhs.put("/controlMore-web/Avaliacao", new AvaliacaoViewHelper());
    	vhs.put("/controlMore-web/Categoria", new CategoriaViewHelper());
    	vhs.put("/controlMore-web/Home", new HomeViewHelper());
    	
    }// construtor Servlet
    
    
    /** 
     * TODO Descrição do Método
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    		IOException {
    	doProcessRequest(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcessRequest(request, response);
	}
	
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		//Obtêm a uri que invocou esta servlet (O que foi definido no method do form html)
		String uri = request.getRequestURI();
		
		//Obtêm a operação executada
		String acao = request.getParameter("acao");
		
		//Obtêm um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		
		//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
		EntidadeDominio entidade =  vh.getEntidade(request);

		//Obtêm o command para executar a respectiva operação
		ICommand command = commands.get(acao);
		
		/*Executa o command que chamará a fachada para executar a operação requisitada
		 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);
		
		/*
		 * Executa o método setView do view helper específico para definir como deverá ser apresentado 
		 * o resultado para o usuário
		 */
		vh.setView(resultado, request, response);
	
	}
}

