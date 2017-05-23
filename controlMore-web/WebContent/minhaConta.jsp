<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ControlMore</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
	<div class="col-md-6">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Dados da conta
		  	</div>
		  	<div class="panel-body">
    			<form action="Pessoa" method="POST">    
    				<div class="row">      
		          		<div class="col-md-3" hidden >
		          		 	Id 
		          		 </div>
						<div class="col-md-4" hidden>
					  		<input type="text" name="txtId" class="form-control" value="<%= pessoa.getId()%>" readonly="readonly" ><br>
						</div>
					</div>
					<div class="row">      
		            	<div class="col-md-3"> 
		            		Nome Completo 
		            	</div>
						<div class="col-md-9">
					  		<input type="text" name="txtNome" class="form-control" value="<%= pessoa.getNome()%>"><br>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3"> 
							Email 
						</div>
						<div class="col-md-9">
					  		<input type="email" name="txtEmail" class="form-control" value="<%=pessoa.getUsuario().getEmail()%>"><br>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3"> 
							Senha 
						</div>
						<div class="col-md-9">
					  		<input type="password" name="txtSenha" class="form-control" placeholder="******" ><br>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3"> 
							Telefone 
						</div>
						<div class="col-md-4">
						  	<input type="text" name="txtTelefone" class="form-control" data-toggle="tooltip" title="Preencha seu telefone" value="<%= pessoa.getTelefone()%>"><br>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3"> 
							Data de Nascimento 
						</div>
						<div class="col-md-4">
						  	<input type="date" name="txtDtNascimento" class="form-control" data-toggle="tooltip" title="Insira sua Data de Nascimento" value="<%= pessoa.getDtNascimento() %>"><br>
						</div>
					</div>
	
		   	  	  	<div class="col-md-6">
						<input type='submit' class="btn btn-default" id='acao' name='acao' value='excluir'>
					</div>
					<div class="col-md-6" align="right">
						<input type='submit' class="btn btn-default" id='acao' name='acao' value='alterar'>
						
		      	  	</div>
  				</form><!-- end form -->
  			</div>
  		</div>
  	</div>
</div>
</body>
</html>