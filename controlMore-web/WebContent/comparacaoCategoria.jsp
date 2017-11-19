<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/graficoCat.css" rel="stylesheet">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>


<title>Control More</title>
</head>
<body>
<c:import url="menu.jsp" />
<c:set value="${categoria}" var="categorias" scope="request" />

<!-- //FROM menu
<div class="row affix-row"> 
  <div class="col-sm-3 col-md-2 affix-sidebar"> 
    <div class="container-fluid" -->	
	  <div class="row" align="center">
	    <form action="RelCat?acao=consultar" method="post">
		  <label class="col-xs-4 col-sm-2 col-md-1">Data Inicial</label>
		  <div class="col-xs-8 col-sm-4 col-md-2">
		    <input type="date" name="txtDataInicial" class="form-control"
			  data-toggle="tooltip" title="Início" required autofocus><br>
		  </div>
		  <label class="col-xs-4 col-sm-2 col-md-1">Data Final</label>
		  <div class="col-xs-8 col-sm-4 col-md-2">
		    <input type="date" name="txtDataFinal" class="form-control"
			  data-toggle="tooltip" title="Início" required autofocus><br>
		  </div>
		  <div class="form-group">
        	<label class="col-xs-4 col-sm-2 col-md-1">Categoria 1</label>
        	<div class="col-xs-8 col-sm-4 col-md-2 selectContainer">
              <select class="form-control" name="size">
                <option value="">Escolha uma categoria</option>
                <c:forEach var="categoria" items="${categorias}">
   				  <option value="${categoria.id}">${categoria.descricao}</option>
				</c:forEach >
              </select><br>
        	</div>
    	  </div>
    	  <div>
        	<label class="col-xs-4 col-sm-2 col-md-1">Categoria 2</label>
        	<div class="col-xs-8 col-sm-4 col-md-2 selectContainer">
              <select class="form-control" name="size">
                <option value="">Escolha uma categoria</option>
                <c:forEach var="categoria2" items="${categorias}">
   				  <option value="${categoria2.id}">${categoria2.descricao}</option>
				</c:forEach >
              </select><br>
        	</div>
    	  </div>
		  <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-2 col-md-offset-5">
		    <button class="btn btn-info btn-block" type="submit" name="acao"
			  value="filtrar" placeholder="Filtrar">Filtrar</button><br>
		  </div>
		</form>
	  </div>
	  <div class="row">
	    <div class="col-md-4">
		  <div class="panel panel-info"><!--  really need it? -->
			<div class="panel-heading text-center">Resumo por categorias</div>
			<div class="panel-body">
			  <form action="acao=salvar" method="POST">
				<table class="table table-hover">
				  <thead>
				  <!-- cabeçalho -->
					<tr>
					<!-- linha -->
					  <!-- colunas -->
					  <th>Categoria</th>
					  <th>Gastos</th>
					  <!-- <th >% Salário</th>-->
					</tr>
				  </thead>
				  <!-- end cabeçalho -->
				  <tbody>
				  <!-- corpo da tabela -->
				    <!-- colunas -->
					<tr>
					<!-- Linha -->
					  <!-- Colunas -->
					  <td></td>
					  <td>R$</td>
					</tr>
					<!-- end linha -->
				  </tbody>
				</table>
		  	  </form>
			</div>
		  </div>
		</div>
		<div class="col-md-8">
			<div id="grafico"></div>
		</div>
	  </div>
    </div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row -->

</body>
</html>