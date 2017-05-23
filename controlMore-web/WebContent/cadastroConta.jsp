<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Conta</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
	<div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastro de Conta
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					<div class="row">
						<div class="col-md-3">
							<label>Tipo de conta</label>
						</div>
						<div class="col-md-9">
							<select class='form-control'>
								<option>Poupança</option>
								<option>Corrente</option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">
							<label>Banco</label>
						</div>
						<div class="col-md-9">
							<input type="text" name="txtBanco" class="form-control">
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">
							<label>Saldo Inicial</label>
						</div>
						<div class="col-md-9">
							<input type="text" name="txtsaldo" class="form-control">
						</div>
					</div>
					
					<div class="col-md-12">
						<label>Info.</label>
						<textarea class="form-control" rows="9"></textarea><br>
					</div>
					
					<div class="row" align="center">
						<button type='submit' class="btn btn-primary" id='acao'><a href="principal.jsp">Salvar</a></button>
					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
	
	<div class="col-md-5">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastro de Cartão 	
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					<div class="row">
						<div class="col-md-3">
							<label>Descrição</label>
						</div>
						<div class="col-md-9">
							<input type="text" name="txtDescricao" class="form-control">
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">
							<label>Limite</label>
						</div>
						<div class="col-md-9">
							<input type="text" name="txtQtdeDias" class="form-control">
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">
						<label>Conta para pagamento</label>
						</div>
						<div class="col-md-8">
							<select class='form-control'>
								<option>lista de contas</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">
							<label>Facha dia</label>
						</div>
						<div class="col-md-2">
							<select class='form-control'>
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
						
						<div class="col-md-3">
							<label>Vence dia</label>
						</div>
						<div class="col-md-2">
							<select class='form-control'>
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 col-md-offset-10">
						<button type='submit' class="btn btn-primary" id='acao'><a href="principal.jsp">Salvar</a></button>
					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
</div><!-- end container -->
</body>
</html>