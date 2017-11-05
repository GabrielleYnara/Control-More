<%//@page import="br.com.controlmore.dominio.Conta"%>
<%//@page import="br.com.controlmore.dominio.Frequencia"%>
<%//@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom styles -->
<link rel="stylesheet" href="css/principal.css">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>ControlMore</title>
    
<script type="text/javascript">
    function indisponivel(){
    	alert("Sinto muito! \n"
    		 +"Esta funcionalidade ainda não está disponível!");
    }
</script>

</head>
<body>
<c:import url="menu.jsp" />
<!-- //FROM menu
<div class="row affix-row"> 
  <div class="col-sm-3 col-md-2 affix-sidebar"> 
    <div class="container-fluid" -->
<% request.getSession().setAttribute("saida", null); %>
<% request.getSession().setAttribute("entrada", null); %>

<h4>Bem vindo(a) ${usuario.nome}</h4>

	<div class=" col-sm-4 col-md-3">
	  <div class="bs-callout bs-callout-info " > <!-- Callout Saldo -->
	  	<div align="center">
	      <h4> R$ </h4>
	  		<p data-toggle="tooltip" data-placement="right" title="Saldo de todas as contas">Saldo Geral</p>
	  	</div>
	  </div> <!-- Fim Callout Saldo -->
  	
  	  <!-- Botão Registrar Entrada  -->
  	  <div>
  	    <a type="button" class="btn btn-success btn-lg col-xs-12" id="espaco" href="entradaCompleta.jsp">
  	      Registrar Entrada <span class="glyphicon glyphicon-arrow-up"></span>
  	    </a>
  	  </div>
  	  
  	  <!-- Botão Registrar Saida  -->
  	  <div >
  	    <a type="button" class="btn btn-warning btn-lg col-xs-12" id="espaco" href="saidaCompleta.jsp">
  	      Registrar Saida <span class="glyphicon glyphicon-arrow-down"></span>
  	    </a>
  	  </div>
  </div> <!-- Fim col-xs-12 col-sm-4 col-md-2 -->
  
	<div class="painel col-xs-12 col-sm-8 col-md-6"><!-- Resumo Financeiro -->
		<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Resumo financeiro
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
  					<img src="img/grafico.png" alt="grafico">
			  		<div align="right">
			    		<p>Saldo Geral <saldo>R$0,00</saldo></p>
			    		<input type="checkbox" id="nPoupanca"> Não incluir saldo de conta poupança
			  		</div><!-- end right -->
			  		<ul>
			    		<li>
			      			<i class="glyphicon glyphicon-piggy-bank"></i><p><strong>Carteira</strong> R$0,00</p>
			      			<p>Outros</p>
			    		</li><!-- end item lista -->
			    		<li>
			      			<i class="glyphicon glyphicon-picture"></i><p><strong>Itaú</strong> R$0,00</p>
			      			<p>Conta Corrente</p>
			    		</li><!-- end item lista -->
			    		<li>
			      			<i class="glyphicon glyphicon-picture"></i><p><strong>Itaú</strong> R$0,00</p>
			      			<p>Conta Poupança</p>
			    		</li><!-- end item lista -->
			  		</ul><!-- end lista bancos -->
			  	</form>
			</div>
		</div>
	</div><!-- end painel resumo financeiro -->
	
	<div class="col-xs-12 col-sm-6 col-md-3"><!-- Painel de Contas a Pagar e Receber -->
	  	<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Contas à Pagar
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
		    		<h5>contas atrasadas <i class="glyphicon glyphicon-thumbs-down"></i></h5>
		    		<ul>
		      			<li>alguma conta 02/08 R$X,XX</li>
		    		</ul><!-- end lista proximas -->
		    		<h5>próximas <spam class="glyphicon glyphicon-thumbs-down"></spam></h5>
		    		<ul>
			  			<li>alguma conta 02/08 R$X,XX</li>
					</ul><!-- end lista proximas -->
				</form>
			</div>
		</div><!-- end painel de Contas a Pagar -->
		  
		<div><!-- Painel de Contas a Receber -->
			<div class="panel panel-info"> <!-- Painel Saída Simples -->
				<div class="panel-heading text-center">
					Contas à Receber
				</div>
				<div class="panel-body form-group">
					<form action="" method="POST">
			 			<h5>Recebimentos Atrasadas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
						<ul>
							<li>alguma conta 02/08 R$X,XX</li>
						</ul><!-- end lista -->
						<h5>próximas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
						<ul>
							<li>alguma conta 02/08 R$X,XX</li>
						</ul><!-- end lista-->
					</form>
				</div>
			</div>
		</div><!-- end painel de contas a receber -->
	</div><!-- Painel de Contas a Pagar e Receber -->

	<div class="col-xs-12 col-sm-6 col-md-3" ><!-- Resumo de metas e cartão-->
  		<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Resumo de Metas
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
		    		<ul>
		      			<li>
					        <p><strong>Carro</strong> 5%</p>
						    <p>Projetada: R$0,00</p>
						    <p>Realizada: R$0,00</p>
						</li>
						<li>
						    <p><strong>Viagem</strong> 10%</p>
						    <p>Projetada: R$0,00</p>
						    <p>Realizada: R$0,00</p>
			  			</li>
		    		</ul>
		    	</form>
		    </div>
  		</div><!-- end resumo Metas -->
	
  		<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Cartão de crédito
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
					<ul>
			  			<li>
		        			<i class="glyphicon glyphicon-credit-card"></i><label>Master Itaú</label>
					        <p>Fatura Atual:R$0,00</p>
					        <p>Limite:R$0,00</p>
					        <button class="btn btn-default" id="VerFatura" type="submit">Ver Fatura</button>
			  			</li><!-- end item lista -->
			  			<li>
		        			<i class="glyphicon glyphicon-credit-card"></i><label>Visa BB</label>
					        <p>Fatura Atual:R$0,00</p>
					        <p>Limite:R$0,00</p>
					        <button class="btn btn-default" id="VerFatura" type="submit">Ver Fatura</button>
			  			</li><!-- end item lista -->
					</ul><!-- end lista cartões -->
				</form>
			</div>
  		</div><!-- end painel resumo cartão -->
 	</div><!-- end Resumo de metas e cartão -->
 	
 	</div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row -->
</body>
</html>