<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


    <title>Simple Sidebar - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="plugins/bootstrap/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="css/menu.css">

</head>
<body>
<div class="row affix-row">
    <div class="col-sm-3 col-md-2 affix-sidebar">
		<div class="sidebar-nav">
			<div class="navbar navbar-default" role="navigation">
			    <div class="navbar-header">
			      	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
				    	<span class="sr-only">Toggle navigation</span>
				      	<span class="icon-bar"></span>
				      	<span class="icon-bar"></span>
				      	<span class="icon-bar"></span>
				    </button>
				    <span class="visible-xs navbar-brand">Sidebar menu</span>
			    </div>
				<div class="navbar-collapse collapse sidebar-navbar-collapse">
					<ul class="nav navbar-nav" id="sidenav01">
				    	<li class="navbar-header">
							<h3>
								<img src="img/Logo.png" alt="Control More logo" height="50" width="100">
							  	<br><br>
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
				        <li class="active">
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
				</div><!--/.nav-collapse -->
		    </div>
  		</div>
	</div>

<div class="col-sm-9 col-md-10 affix-content">
		<div class="container">
		
</div>
</div>
    <!-- jQuery -->
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
</body>
</html>