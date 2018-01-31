<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cadastro Categoria</title>
</head>
<body>
<%@include file="menu.jsp"%>
<c:set value="${categoria}" var="categoria" />

	  <div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastrar Categoria
		  	</div>
		  	<div class="panel-body">
		  	<c:if test="${not empty categoria}">
		  		<form action="Categoria?acao=alterar" method="POST">
		  	</c:if>
		  	<c:if test="${empty categoria }">
		  		<form action="Categoria?acao=salvar" method="POST">
		  	</c:if>
					<div class="radio" >
					  	<label>
					    	<input type="radio" name="optCategoria" value="categoria" checked>
					    	Categoria
					  	</label>
					</div>
					<div class="radio">
					  	<label hidden>
					    	<input type="radio" name="optCategoria" value="subcategoria" >
					    	Subcategoria
					  	</label>
					</div>
					<label>Nome</label>
					  <c:if test="${not empty categoria.descricao}">
						<input type="text" name="txtDescricao" class="form-control" 
					  	value="${categoria.descricao}"/>
					  </c:if>
					  <c:if test="${empty categoria.descricao}">
					    <input type="text" name="txtDescricao" class="form-control" >
					  </c:if>
					
					  
					<!-- O sistema não esta preparado para configuração de cores 
					<label>Cor</label>
					<input type="text" name="txtCor" class="form-control"
					  <c:if test="${not empty categoria.cor}"> 
					    <c:out value="${categoria.cor}"/>
					  </c:if>
					> -->
					<c:if test="${not empty categoria.id}">
					<div hidden>
						<input type="text" name="txtId" class="form-control" 
					  	value="${categoria.id}"/>
					  	</div>
					  </c:if>
					<div align="left" class="col-xs-6 col-sm-6 col-md-6">
		    			<a href="javascript:location.href='Home?acao=resumo';" class="btn btn-default">Cancelar</a>
	      			</div><!-- end row -->
		  			<div align="right" class="col-xs-6 col-sm-6 col-md-6">
		    			<button class="btn btn-primary" type="submit">Salvar</button>		    			
	      			</div>
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