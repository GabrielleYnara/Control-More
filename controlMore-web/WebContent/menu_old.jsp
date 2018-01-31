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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">

<!-- Custom CSS -->
<link href="plugins/bootstrap/css/simple-sidebar.css" rel="stylesheet">
<link rel="stylesheet" href="css/menu.css">

<!-- Optional theme -->
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-theme.min.css" >
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<c:import url="WEB-INF/util/mensagem.jsp" />
<%
	Resultado resultado = (Resultado) request.getSession().getAttribute("pessoa");
	Pessoa pessoa = new Pessoa();
	// TODO
//	pessoa = (Pessoa) resultado.getEntidades().get(0);
%>
<c:set value="${pessoa_new}" var="usuario" scope="session" />
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
<body >
<!-- <div class="row affix-row">
  <header>
     <div class="col-xs-12 col-sm-3 col-md-2 affix-sidebar" >
		<div id="menu" class="sidebar-nav">
			<div class="navbar navbar-default" role="navigation">
			    <div class="navbar-header">
			      	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
				    	<span class="sr-only">Toggle navigation</span>
				      	<span class="icon-bar"></span>
				  		<span class="icon-bar"></span>
				      	<span class="icon-bar"></span>
				    </button>
				    <span class="visible-xs navbar-brand">Control More</span>
			    </div>
				<div class="navbar-collapse collapse sidebar-navbar-collapse">
					<ul class="nav navbar-nav" id="sidenav01">
				    	<li class="navbar-header">
							<h3>
								<img src="img/Logo.png" alt="Control More logo" height="50" width="100">
							  	<br>
							</h3>
						</li>
				   
				   		<li><a href="principal.jsp"><span class="glyphicon glyphicon-lock"></span> Home</a></li>
				        <li>
				        	<a href="#" data-toggle="collapse" data-target="#toggleDemo" data-parent="#sidenav01" class="collapsed">
				          		<span class="glyphicon glyphicon-cloud"></span> Relatório <span class="caret pull-right"></span>
				          	</a>
				          	<div class="collapse" id="toggleDemo" style="height: 0px;">
				            	<ul class="nav nav-list">
				              		<li><a href="projecaoEconomia.jsp">Projeção de Economia</a></li>
									<li><a href="analisarAtraso.jsp">Análise de Atrasos</a></li>
									<li><a href="javascript:location.href='Filtro?acao=consultar&txtConsulta=EntradaSaida';">Lançamentos</a></li>
									<li><a href="javascript:consultarRelCat();">Resumo por Categoria</a></li>
								</ul>
				          	</div>
				        </li>
				        <li><a href="javascript:consultarMeta();"> <span class="glyphicon glyphicon-calendar"></span>Metas</a></li>
				        <li>
				        	<a href="#" data-toggle="collapse" data-target="#toggleDemo2" data-parent="#sidenav01" class="collapsed">
				          		Mais
				          	</a>
				          	<div class="collapse" id="toggleDemo2" style="height: 0px;">
				            	<ul class="nav nav-list">
				             		<li><a href="#">Agenda</a></li>
									<li><a href="#">Calculadora</a></li>
									<li><a href="#">Conversor de Moeda</a></li>
									<li><a href="Info.jsp">Info</a></li>
				            	</ul>
				          	</div>
				        </li>
				        <li><a href="faleConosco.jsp">Fale Conosco</a></li>
						<li><a href="minhaConta.jsp">Minha Conta </a></li>
						<li><a href="javascript:location.href='Pessoa?acao=logout'">Log Out</a></li>
					</ul>
				</div><!--/.nav-collapse 
		    </div>
  		</div>
	</div>
  	-->
  	<header>
  	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">Control More</a>
	      
		</div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="Home?acao=resumo"><span class="glyphicon glyphicon-lock"></span> Home</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				   <span class="glyphicon glyphicon-cloud"></span> Relatório <span class="caret"></span>
				</a>
	          <ul class="dropdown-menu">
			    <li hidden><a href="projecaoEconomia.jsp">Projeção de Economia</a></li>
			    <li><a href="analisarAtraso.jsp">Análise de Atrasos</a></li>
			    <li><a href="javascript:location.href='Filtro?acao=consultar&txtConsulta=EntradaSaida';">Lançamentos</a></li>
			    <li><a href="javascript:consultarRelCat();">Resumo por Categoria</a></li>
			    <li><a href="javascript:location.href='Categoria?acao=consultar';">Comparação de Categorias</a>
		      </ul>
	        </li>
	        <li><a href="javascript:consultarMeta();"> <span class="glyphicon glyphicon-calendar"></span>Metas</a></li>
	        <li class="dropdown">
	          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				   Mais <span class="caret"></span>
				</a>
	            <ul class="dropdown-menu">
	              <li><a href="#">Agenda</a></li>
				  <li><a href="#">Calculadora</a></li>
				  <li><a href="#">Conversor de Moeda</a></li>
				  <li><a href="Info.jsp">Info</a></li>
				  <li><a href="javascript:location.href='Categoria?acao=consultar';">Listar Categorias</a></li>
	            </ul>
	        </li>
	        <li><a href="faleConosco.jsp">Fale Conosco</a></li>
			<li><a href="minhaConta.jsp">Minha Conta </a></li>
			<li><a href="javascript:location.href='Pessoa?acao=logout'">Log Out</a></li>
			<li><a href="testeJSTL.jsp">testeJSTL</a></li>
		  </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
  </header> <!-- Fim de cabeçalho -->
  <div class="col-sm-12 col-md-12 affix-content"> <!-- col-sm-9 col-md-10 affix-content-->
    <div class="container-fluid">
  

<c:import url="WEB-INF/util/mensagem.jsp"></c:import>
</body>
</html>