package br.com.controlmore.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dao.AnaliseDAO;
import br.com.controlmore.dao.AvaliacaoDAO;
import br.com.controlmore.dao.CategoriaDAO;
import br.com.controlmore.dao.CompararCategoriasDAO;
import br.com.controlmore.dao.ContaDAO;
import br.com.controlmore.dao.EntradaDAO;
import br.com.controlmore.dao.FiltroDAO;
import br.com.controlmore.dao.IDAO;
import br.com.controlmore.dao.MetaDAO;
import br.com.controlmore.dao.PessoaDAO;
import br.com.controlmore.dao.QuestionarioDAO;
import br.com.controlmore.dao.RelatorioCategoriaDAO;
import br.com.controlmore.dao.ResumoDAO;
import br.com.controlmore.dao.SaidaDAO;
import br.com.controlmore.dao.aPagarDAO;
import br.com.controlmore.dao.upLoadDAO;
import br.com.controlmore.dominio.AvaliacaoGasto;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Filtro;
import br.com.controlmore.dominio.Meta;
import br.com.controlmore.dominio.Pessoa;
import br.com.controlmore.dominio.Questionario;
import br.com.controlmore.dominio.RelatorioCategoria;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.negocio.Avaliacao;
import br.com.controlmore.negocio.CompletarDtCadastro;
import br.com.controlmore.negocio.IStrategy;
import br.com.controlmore.negocio.SimularMeta;
import br.com.controlmore.negocio.ValidarData;
import br.com.controlmore.negocio.VerificarSaldo;
import br.com.controlmore.negocio.compararCategorias;
import br.com.controlmore.negocio.ordenarComparacao;
import br.com.controlmore.vm.AnaliseVM;
import br.com.controlmore.vm.ResumoVM;
import br.com.controlmore.vm.aPagarVM;
import br.com.controlmore.vm.aReceberVM;
import br.com.controlmore.vm.compararCategoriaVM;
import br.com.controlmore.vm.upLoadVM;

public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, será indexado pelo nome da entidade 
	 * O valor é uma instância do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade;
	 * O valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	public Fachada(){
		/* Instancioando o Map para os DAOS */
		daos = new HashMap<String, IDAO>();
		
		/* Instanciando Map para as Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		/* Criando instâncias dos DAOs que serão utilizados*/
		PessoaDAO pessoaDAO = new PessoaDAO();
		MetaDAO metaDAO = new MetaDAO();
		EntradaDAO entradaDAO = new EntradaDAO();
		SaidaDAO saidaDAO = new SaidaDAO();
		RelatorioCategoriaDAO relCatDAO = new RelatorioCategoriaDAO();
		FiltroDAO filtroDAO = new FiltroDAO();
		QuestionarioDAO questionarioDAO = new QuestionarioDAO();
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		upLoadDAO upLoadDAO = new upLoadDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Pessoa.class.getName(), pessoaDAO);
		daos.put(Meta.class.getName(), metaDAO);
		daos.put(Entrada.class.getName(), entradaDAO);
		daos.put(Saida.class.getName(), saidaDAO);
		daos.put(RelatorioCategoria.class.getName(), relCatDAO);
		daos.put(Filtro.class.getName(), filtroDAO);
		daos.put(Questionario.class.getName(), questionarioDAO);
		daos.put(AvaliacaoGasto.class.getName(), avaliacaoDAO);
		daos.put(Categoria.class.getName(), categoriaDAO);
		daos.put(upLoadVM.class.getName(), upLoadDAO);
		
		/* Criando instâncias de regras de negócio a serem utilizados*/		
		CompletarDtCadastro cDtCadastro = new CompletarDtCadastro();
		SimularMeta simularMeta = new SimularMeta();
		VerificarSaldo verificarSaldo = new VerificarSaldo();
		ValidarData validarData = new ValidarData();
		
		/* Criando uma lista para conter as regras de negócio 
		 * quando a operação for salvar */
		List<IStrategy> rnsSalvarPessoa = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarMeta = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarEntrada = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarSaida = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarQuestionario = new ArrayList<IStrategy>();
		
		/* Criando uma lista para conter as regras de negócio 
		 * quando a operação for salvar */
		List<IStrategy> rnsConsultarMeta = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarRelCat = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarFiltro = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarAvaliacao = new ArrayList<IStrategy>();

		/* Adicionando as regras a serem utilizadas na operação salvar Pessoa*/
		rnsSalvarPessoa.add(cDtCadastro);
		
		/* Adicionando as regras a serem utilizadas na operação salvar Meta*/
		rnsSalvarMeta.add(cDtCadastro);
		/* Adicionando as regras a serem utilizadas na operação consultar Meta*/
		rnsConsultarMeta.add(simularMeta);
		
		/* Adicionando as regras a serem utilizadas na operação salvar Entrada*/
		rnsSalvarEntrada.add(cDtCadastro);
		
		/* Adicionando as regras a serem utilizadas na operação salvar Entrada*/
		rnsSalvarSaida.add(cDtCadastro);
		
		/* Adicionando as regras a serem utilizadas na operação consultar Filtro */
		rnsConsultarFiltro.add(validarData);
		
		/* Cria o mapa que poderá conter todas as listas de regras de negócio específica por operação */
		Map<String, List<IStrategy>> rnsPessoa = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsMeta = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsEntrada = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsSaida = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsRelCat = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsFiltro = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsQuestionario = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsAvaliacao = new HashMap<String, List<IStrategy>>();

		/* Adiciona a listra de regras na operação salvar */
		rnsPessoa.put("SALVAR", rnsSalvarPessoa);
		rnsMeta.put("SALVAR", rnsSalvarMeta);
		rnsEntrada.put("SALVAR", rnsSalvarEntrada);
		rnsSaida.put("SALVAR", rnsSalvarSaida);
		rnsQuestionario.put("SALVAR", rnsSalvarQuestionario);
		
		/* Adiciona a listra de regras na operação colsultar*/
		rnsMeta.put("CONSULTAR", rnsConsultarMeta);
		rnsFiltro.put("CONSULTAR", rnsConsultarFiltro);
		
		/* Adiciona o mapa com as regras indexadas pelas operações no mapa geral indexado 
		 * pelo nome da entidade */
		rns.put(Pessoa.class.getName(), rnsPessoa);	
		rns.put(Meta.class.getName(), rnsMeta);
		rns.put(Entrada.class.getName(), rnsEntrada);
		rns.put(Saida.class.getName(), rnsSaida);
		rns.put(RelatorioCategoria.class.getName(), rnsRelCat);
		rns.put(Filtro.class.getName(), rnsFiltro);
		rns.put(Questionario.class.getName(), rnsQuestionario);
		rns.put(Avaliacao.class.getName(), rnsAvaliacao);
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			dao.salvar(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);
		}
		
		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
				dao.excluir(entidade);
				//List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				//entidades.add(entidade);
				//resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);			
		}
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "CONSULTAR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			resultado.setEntidades(dao.consultar(entidade));
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(0));
		resultado.getEntidades().add(entidade);		
		return resultado;
	}

	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
		
			if(regras != null){
				for(IStrategy s: regras){
					String m;
					if(nmClasse.equals("br.com.controlmore.dominio.Filtro")){
						Filtro filtro = (Filtro) entidade;
						m = s.processar(filtro.getDtInicio(),filtro.getDtFinal());
					}else{
						m = s.processar(entidade);
					}
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
	
	public Resultado login(EntidadeDominio entidade){

		resultado = new Resultado();
		if(entidade !=null){
			Pessoa pessoa = (Pessoa) entidade;
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoaResult = pessoaDAO.autenticar(pessoa);
		
			if (pessoaResult != null){
				List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
				lista.add(pessoaResult);
				resultado.setEntidades(lista);
				
			}else{
				resultado.setMsg("E-mail ou senha inválidos!");
			}
		}
		return resultado;
	}
	public Resultado logout(EntidadeDominio entidade){
		resultado = new Resultado();
		return resultado;
	}
	
	public Resultado resumo() {
		
		Resultado resultado = new Resultado();
		ResumoVM resumoVM = new ResumoVM();
		resultado.setModeloVisao(resumoVM);
		
		// Criar objeto de algum DAO diretamente.
		ResumoDAO rDAO = new ResumoDAO();
		
		// Executar consulta para recuperar saldo.
		double saldoS = rDAO.saldoSaida();
		double saldoE = rDAO.saldoEntrada();
		resumoVM.setSaldo(saldoE-saldoS);
		
		
		// Obter dados para o grafico geral mensal.
		ResumoVM rVM = rDAO.graficoMensal();
		if(rVM.getDias().size()>0){//Adicionando os dias
			for(int i=0; i<rVM.getDias().size(); i++){
				resumoVM.setDias(rVM.getDias().get(i));
			}
		}
		if(rVM.getcRecebidas().size()>0){//Adicionando as contas recebidas
			for(int i=0; i<rVM.getcRecebidas().size(); i++){
	   			resumoVM.setcRecebidas(rVM.getcRecebidas().get(i));
			}
		}		
		if(rVM.getcPagas().size()>0){//Adicionando as contas pagas
			for(int i=0; i<rVM.getcPagas().size(); i++){
	   			resumoVM.setcPagas(rVM.getcPagas().get(i));
			}
		}
		
		//Obter dados das contas proximas (a receber e a pagar)
		rVM= rDAO.proximosVencimentos();
		if(rVM.getaPagar().size()>0){//Adicionando contas a pagar proximas ao vencimento
			for(int i =0; i<rVM.getaPagar().size(); i++){
				resumoVM.setaPagar(rVM.getaPagar().get(i));
			}
		}
		rVM= rDAO.proximosRecebimentos();
		if(rVM.getaReceber().size()>0){//Adicionando contas a receber nos proximos dias
			for(int i =0; i<rVM.getaReceber().size(); i++){
				resumoVM.setaReceber(rVM.getaReceber().get(i));
			}
		}
		
		//Obter dados das contas vencidas (a receber e a pagar)
		rVM= rDAO.aPagarVencidas();
		if(rVM.getaPagarVencida().size()>0){//Adicionando contas vencidas
			for(int i =0; i<rVM.getaPagarVencida().size(); i++){
				resumoVM.setaPagarVencida(rVM.getaPagarVencida().get(i));
			}
		}
		rVM= rDAO.aReceberAtrasadas();
		if(rVM.getaReceberAtrasada().size()>0){//Adicionando contas a receber nos proximos dias
			for(int i =0; i<rVM.getaReceberAtrasada().size(); i++){
				resumoVM.setaReceberAtrasada(rVM.getaReceberAtrasada().get(i));
			}
		}
		return resultado;
		
	}

	/**
	 * Será responsável por montar os objetos necessários para tela de Cadastro de Contas a Receber
	 */
	@Override
	public Resultado contaReceber() {
		Resultado resultado = new Resultado();
		aReceberVM aReceberVM = new aReceberVM();
		resultado.setModeloVisao(aReceberVM);
		
		// Criar objeto ContaDAO diretamente para realizar consulta
		ContaDAO cDAO = new ContaDAO();
		//Trazer resultados de contas cadastradas
		List<EntidadeDominio> contas = new ArrayList<EntidadeDominio>();
		Conta ct = new Conta();
		contas.addAll(cDAO.consultar(ct));
		if(contas.size()>0){
			for(int i =0; i<contas.size(); i++){
				aReceberVM.setConta((Conta)contas.get(i)); 
			}
		}
		
		return resultado;
	}
	
	public Resultado contaPagar(){
		Resultado resultado = new Resultado();
		aPagarVM aPagarVM = new aPagarVM();
		resultado.setModeloVisao(aPagarVM);
		
		//Instacia DAO
		aPagarDAO aPagarDAO = new aPagarDAO();
		
		//Obter dados das contas e cartoes cadastrados
		aPagarVM pVM= aPagarDAO.contasCartoes();
		if(pVM.getContasCartoes().size()>0){//Adicionando contas vencidas
			for(int i =0; i<pVM.getContasCartoes().size(); i++){
				aPagarVM.setContasCartoes(pVM.getContasCartoes().get(i));
			}
		}
		
		//Obter dados das categorias cadastradas
		CategoriaDAO cDAO = new CategoriaDAO();
		//Trazer resultados
		List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
		Categoria ct = new Categoria();
		categorias.addAll(cDAO.consultar(ct));
		if(categorias.size()>0){
			for(int i =0; i<categorias.size(); i++){
				aPagarVM.setCategoria((Categoria)categorias.get(i)); 
			}
		}
		return resultado;
	}


	@Override
	public Resultado comparar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		Resultado auxR = (Resultado)entidade;
		compararCategoriaVM compararVM = (compararCategoriaVM) auxR.getModeloVisao();
		resultado.setModeloVisao(compararVM);
		String msg = null;
		
		//instancia da regra de negorio
		if(compararVM.getIdCategoria1()!=0 && compararVM.getIdCategoria2()!=0){
			compararCategorias compararRN = new compararCategorias();
			msg = compararRN.processar(entidade);
		}
		if(msg==null){//Se mensagem for vazia continua 
			CompararCategoriasDAO cDAO = new CompararCategoriasDAO();
			compararVM = cDAO.consultar(compararVM);
			/*ordenarComparacao ordenarRN = new ordenarComparacao();
			ordenarRN.processar(compararVM);*/
		}else{//Se for vazia, retorna mensagem
			resultado.setMsg(msg);
		}
		return resultado;
	}


	@Override
	public Resultado analisar() {
		Resultado resultado = new Resultado();
		AnaliseVM analiseVM = new AnaliseVM();
		resultado.setModeloVisao(analiseVM);
		
		// Criar objeto ContaDAO diretamente para realizar consulta
		AnaliseDAO aDAO = new AnaliseDAO();
		//Trazer resultados de contas cadastradas
		analiseVM= aDAO.aPagarVencidas();
		
		return resultado;
	}
}
