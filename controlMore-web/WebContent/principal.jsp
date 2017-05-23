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

<title>ControlMore</title>
    
<script type="text/javascript">
    function indisponivel(){
    	alert("Sinto muito! \n"
    		 +"Esta funcionalidade ainda não está disponível!");
    }
</script>

</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
<% request.getSession().setAttribute("saida", null); %>
<h4><%out.print("Bem Vindo(a) " + pessoa.getNome()); %></h4>

	<div id="balanco" class="col-md-2" hidden> <!-- Balanço mês atual e anterior -->
		<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Mês/Ano
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
					<p id="anterior">Você fechou o <strong>MÊS ANTERIOR</strong> com o balanço de <strong>R$X,XX</strong></p>
					<p id="atual">Para o <strong>MÊS ATUAL</strong> a previsão de fechamento é de <strong>R$X,XX</strong></p>
  				</form>
  			</div>
  		</div>
  	</div> <!-- Fim Balanço mês atual e anterior -->
  	<div class="col-md-1"></div>
	<div class="col-md-5">
	  	<div class="panel panel-info"> <!-- Painel Entrada Simples -->
	  		<div class="panel-heading text-center">
			 	Contas à Receber
			</div>
			<div class="panel-body">
				<form action="Entrada?acao=salvar" method="POST">
					<div class="row">
						<div class="col-md-2">
			    			Valor R$
			    		</div>
			    		<div class="col-md-3">
			    			<input type="text" name="txtValor" class="form-control" placeholder="R$0,00" autofocus>
			    		</div>
			    		
						<div class="col-md-2">
							Frequência
						</div>
			    		<div class="col-md-4">
			      			<select class="form-control" name="txtFreq">
				    			<option value="1" >Avulso</option>
								<option value="4" >Mensal</option>
								<option value="2" >Semanal</option>
							</select>
			      		</div>
			      		
			      		<div class="col-md-1">
		    				<a href="cadastroFrequencia.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div>
		  			</div><!-- end row -->
		  
				  	<div class="row">
				    	<div class=col-md-2>
				    		Descrição
				    	</div>
				    	<div class="col-md-9">
				    		<input type="text" name="txtDescricao" class="form-control" autofocus>
				    	</div>
				  	</div><!-- end row -->
				
					<div class="row">	
						<div class="col-md-2">
							Conta/ Cartão
		   				</div>
		   				<div class="col-md-9">
		      				<select class="form-control"  name="txtConta">
			    				<option value="1">Itaú - CC</option>
							   	<option value="2">Itaú - CP</option>
							   	<option value="4">Carteira </option>
							</select>
		    			</div>
		    			<div class="col-md-1">
		    				<a href="cadastroConta.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div>    
					</div><!-- end row -->
				  	
		  			<div class="row" align="center">
		  				<button class="btn btn-primary" type="submit">Salvar</button>
		  			</div>
		  			<div class="col-md-12 text-right">
		   		 		<button class="btn btn-default" type="submit"><a href="entradaCompleta.jsp">Entrada Completa</a></button>
	      			</div><!-- end row -->
	      			
	      		</form>
	      	</div>
		</div><!-- end entrada Simples -->
	</div>

	<div class="col-md-5">
	  	<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Contas à Pagar
			</div>
			<div class="panel-body form-group">
				<form action="Saida?acao=salvar" method="POST">
		  			<div class="row">
						<div class="col-md-2">
			    			Valor R$
			    		</div>
			    		<div class="col-md-3">
			    			<input type="text" name="txtValor" class="form-control" placeholder="R$0,00" autofocus>
			    		</div>
			    		
						<div class="col-md-2">
							Frequência
						</div>
			    		<div class="col-md-4">
			      			<select class="form-control" name="txtFreq">
				    			<option value="1" >Avulso</option>
								<option value="4" >Mensal</option>
								<option value="2" >Semanal</option>
							</select>
			      		</div>
			      		
			      		<div class="col-md-1">
		    				<a href="cadastroFrequencia.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div>
		  			</div><!-- end row -->
		  
				  	<div class="row">
				    	<div class=col-md-2>
				    		Descrição
				    	</div>
				    	<div class="col-md-9">
				    		<input type="text" name="txtDescricao" class="form-control" required autofocus>
				    	</div>
				  	</div><!-- end row -->
				
					<div class="row">	
						<div class="col-md-2">
							Conta/ Cartão
		   				</div>
		   				<div class="col-md-9">
		      				<select class="form-control"  name="txtConta">
			    				<option value="1">Itaú - CC</option>
							   	<option value="2">Itaú - CP</option>
							   	<option value="4">Carteira </option>
							</select>
		    			</div>
		    			<div class="col-md-1">
		    				<a href="cadastroConta.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div>    
					</div><!-- end row -->
		  
		    		<div class="row">	
		      			<div class="col-md-2">
		      				Categoria
		      			</div>
		      			<div class="col-md-5">
					      	<select class="form-control" name="txtCategoria">
							    <option value="1">Alimentação</option>
							    <option value="6">Educacao</option>
							    <option value="3">Lazer</option>
							    <option value="2">Moradia</option>
							    <option value="5">Outros</option>
							    <option value="7">Transporte</option>
							    <option value="4">Vestuario</option>
						   	</select>
						</div>
						<div class="col-md-1">
		    				<a href="cadastroCategoria.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div> 
				    
			    		<div class="col-md-4" align="center" >
			      			<input class="btn btn-primary" type="submit"  name="acao" value="Salvar">	
		        		</div>
		        	</div><!-- end row -->
		        	
	        		<div class="col-md-12 text-center">
	        			<button class="btn btn-default"><a href="saidaCompleta.jsp">Saida Completa</a></button>
		      		</div>
	        	</form>
	      	</div><!-- end painel body-->
		</div><!-- end saida simples -->
	</div>
	
	<div class="col-md-3" hidden><!-- Painel de Contas a Pagare Receber -->
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
	
	<div class="painel col-md-6" hidden><!-- Resumo Financeiro -->
		<div class="panel panel-info"> <!-- Painel Saída Simples -->
			<div class="panel-heading text-center">
				Resumo financeiro
			</div>
			<div class="panel-body form-group">
				<form action="" method="POST">
  					<img src="img\grafico.png" alt="grafico">
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

	<div class="col-md-3" hidden><!-- Resumo de metas e cartão-->
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
</div><!-- end container -->
</body>
</html>