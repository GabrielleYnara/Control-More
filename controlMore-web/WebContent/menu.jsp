<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%@page import="br.com.controlmore.dominio.Pessoa"%>
<%@page import="br.com.controlmore.dominio.Entrada"%>
<%@page import="br.com.controlmore.dominio.Saida"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/menu.css">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css" >

<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js" ></script>

</head>

<body>
<%@include file="WEB-INF/util/mensagem.jsp" %>
<%
	Resultado resultado = (Resultado) request.getSession().getAttribute("pessoa");
	Pessoa pessoa = new Pessoa();
	pessoa = (Pessoa) resultado.getEntidades().get(0);
%>
<script type="text/javascript">
function consultarUsuario(id){
	
	location.href="Pessoa?acao=consultar&txtId="+ id;
}
function consultarMeta(){
	
	location.href="Meta?acao=consultar";
}
function consultarEntrada(){
	
	location.href="Entrada?acao=consultar";
}
function consultarSaida(){
	
	location.href="Saida?acao=consultar";
}
function consultarRelCat(){
	
	location.href="RelCat?acao=consultar";
}
function excluir(){
	if(window.confirm('Tem certeza que deseja excluir?')){
	
	}
function filtro(){
	location.href="Filtro?acao=consultar&"
}
}

</script>
<header> <!-- conteúdo de cabeçalho -->
			   	
<nav class="navbar navbar-default">
  <div class="container-fluid" >
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header" >
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="true">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="principal.jsp">
      	<img src="img/Logo.png" alt="Control More logo" height="50" width="100">
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <!-- <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>-->
      
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="principal.jsp">Home</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatório <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <!-- <li><a href="projecaoEconomia.jsp">Projeção de Economia</a></li>
            <li><a href="javascript:consultarEntrada()">Geral</a></li>
            <li><a href="analisarAtraso.jsp">Análise de Atrasos</a></li>-->
            <li><a href="javascript:location.href='Filtro?acao=consultar&txtConsulta=EntradaSaida';">Lançamentos</a>
            <li><a href="javascript:consultarRelCat();">Resumo por Categoria</a>
          </ul>
        </li>
        <li><a href="javascript:consultarMeta();">Metas</a></li>
        <!-- <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Mais <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Agenda</a></li>
            <li><a href="#">Calculadora</a></li>
            <li><a href="#">Conversor de Moeda</a></li>
            <li><a href="Info.jsp">Info</a></li>
          </ul>
        </li>-->
        <li><a href="faleConosco.jsp">Fale Conosco</a></li>
        <li>
        	<!-- <a href="javascript:consultarUsuario(<%out.print(pessoa.getId());%>)">Minha Conta</a> -->
        	<a href="minhaConta.jsp">Minha Conta</a>
        </li>
        <li><a href="javascript:location.href='Pessoa?acao=logout'">Log Out</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</header> <!-- Fim de cabeçalho -->

</body>
</html>