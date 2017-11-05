<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cadastro de Freqência</title>
</head>
<body>
<%@include file="menu.jsp"%>
	  <div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastrar Frequência
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					<label>Descrição</label>
					<input type="text" name="txtDescricao" class="form-control">
					
					<label>Quantidade de dias</label>
					<input type="text" name="txtQtdeDias" class="form-control">
					
					<div class="row" align="center">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
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