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
    		 +"Esta funcionalidade ainda n�o est� dispon�vel!");
    }
</script>

</head>
<body>
<c:import url="menu.jsp" />
<!-- <div class="row affix-row"> //FROM menu
    <div class="col-sm-3 col-md-2 affix-sidebar"> -->
<% request.getSession().setAttribute("saida", null); %>
<h4>Bem vindo(a) ${usuario.nome}</h4>

	<div id="balanco" class="col-md-3"> <!-- Balan�o m�s atual e anterior -->
		<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
			<div class="panel-heading text-center">
				M�s/Ano
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
					<p id="anterior">Voc� fechou o <strong>M�S ANTERIOR</strong> com o balan�o de <strong>R$X,XX</strong></p>
					<p id="atual">Para o <strong>M�S ATUAL</strong> a previs�o de fechamento � de <strong>R$X,XX</strong></p>
  				</form>
  			</div>
  		</div>
  	</div> <!-- Fim Balan�o m�s atual e anterior -->
	
	<div class="painel col-md-6"><!-- Resumo Financeiro -->
		<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
			<div class="panel-heading text-center">
				Resumo financeiro
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
  					<img src="img/grafico.png" alt="grafico">
			  		<div align="right">
			    		<p>Saldo Geral <saldo>R$0,00</saldo></p>
			    		<input type="checkbox" id="nPoupanca"> N�o incluir saldo de conta poupan�a
			  		</div><!-- end right -->
			  		<ul>
			    		<li>
			      			<i class="glyphicon glyphicon-piggy-bank"></i><p><strong>Carteira</strong> R$0,00</p>
			      			<p>Outros</p>
			    		</li><!-- end item lista -->
			    		<li>
			      			<i class="glyphicon glyphicon-picture"></i><p><strong>Ita�</strong> R$0,00</p>
			      			<p>Conta Corrente</p>
			    		</li><!-- end item lista -->
			    		<li>
			      			<i class="glyphicon glyphicon-picture"></i><p><strong>Ita�</strong> R$0,00</p>
			      			<p>Conta Poupan�a</p>
			    		</li><!-- end item lista -->
			  		</ul><!-- end lista bancos -->
			  	</form>
			</div>
		</div>
	</div><!-- end painel resumo financeiro -->
	
	<div class="col-md-3"><!-- Painel de Contas a Pagar e Receber -->
	  	<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
			<div class="panel-heading text-center">
				Contas � Pagar
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
		    		<h5>contas atrasadas <i class="glyphicon glyphicon-thumbs-down"></i></h5>
		    		<ul>
		      			<li>alguma conta 02/08 R$X,XX</li>
		    		</ul><!-- end lista proximas -->
		    		<h5>pr�ximas <spam class="glyphicon glyphicon-thumbs-down"></spam></h5>
		    		<ul>
			  			<li>alguma conta 02/08 R$X,XX</li>
					</ul><!-- end lista proximas -->
				</form>
			</div>
		</div><!-- end painel de Contas a Pagar -->
		  
		<div><!-- Painel de Contas a Receber -->
			<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
				<div class="panel-heading text-center">
					Contas � Receber
				</div>
				<div class="panel-body form-group">
					<form action="" method="POST">
			 			<h5>Recebimentos Atrasadas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
						<ul>
							<li>alguma conta 02/08 R$X,XX</li>
						</ul><!-- end lista -->
						<h5>pr�ximas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
						<ul>
							<li>alguma conta 02/08 R$X,XX</li>
						</ul><!-- end lista-->
					</form>
				</div>
			</div>
		</div><!-- end painel de contas a receber -->
	</div><!-- Painel de Contas a Pagar e Receber -->

	<div class="col-md-3" ><!-- Resumo de metas e cart�o-->
  		<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
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
	
  		<div class="panel panel-info"> <!-- Painel Sa�da Simples -->
			<div class="panel-heading text-center">
				Cart�o de cr�dito
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
					<ul>
			  			<li>
		        			<i class="glyphicon glyphicon-credit-card"></i><label>Master Ita�</label>
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
					</ul><!-- end lista cart�es -->
				</form>
			</div>
  		</div><!-- end painel resumo cart�o -->
 	</div><!-- end Resumo de metas e cart�o -->
	</div><!--  .row affix-row -->
</div> <!-- .col-sm-3 col-md-2 affix-sidebar -->
</body>
</html>