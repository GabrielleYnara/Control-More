<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cadastro de Cartão</title>
</head>
<body>
<%@include file="menu.jsp"%>
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
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	  </div><!-- end col-4 -->
    </div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row --></body>
</html>