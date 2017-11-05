<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ControlMore</title>
</head>
<body>

<%@include file="menu.jsp"%>
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Analisar Contas à Pagar Atrasadas
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
				  <div class="table-responsive">
					<table class="table table-hover">
			 			<thead><!-- cabeçalho -->
					     	<tr><!-- linha -->
					     		<!-- colunas -->
					        	<th></th>
					        	<th>Descrição</th>
					        	<th>Categoria</th>
					        	<th>Vencimento</th>
					        	<th>Valor</th>
					        	<th>Multa</th>
					        	<th>Juros</th>
					        	<th>Total Hoje</th>
					        	<th>Priorizar?</th>
					      	</tr>
					    </thead><!-- end cabeçalho -->
					    <tbody><!-- corpo da tabela -->
					      	
					      		<!-- colunas -->
					   			<tr>
									<td></td>
									<td>Conta de Luz</td>
									<td>Moradia</td>
									<td>02/09/2016</td>
									<td>R$54,98</td>
									<td>R$3,00</td>
									<td>R$0,86</td>
									<td>R$69,56</td>
									<td>SIM</td>
								</tr>
								
								<tr>
									<td></td>
									<td>Roupa pra festa</td>
									<td>Vestimenta</td>
									<td>02/09/2016</td>
									<td>R$54,98</td>
									<td>R$3,00</td>
									<td>R$0,86</td>
									<td>R$69,56</td>
									<td>Não</td>
								</tr>
								
						</tbody>
					</table>
				  </div>
				  <div class="row" align="left">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
						<button type='submit' class="btn btn-default" id='acao'>Imprimir</button>

					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
 	</div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row -->
</body>
</html>