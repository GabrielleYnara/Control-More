<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="javafx.print.Collation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controlmore.dominio.Saida"%>
<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%@page import="br.com.controlmore.vm.compararCategoriaVM"%>
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
<c:set value="${resultado.modeloVisao.categorias}" var="categorias" />
    <div class="container-fluid">	
	  <div class="row" align="center">
	    <form action="Comparar?acao=comparar" method="post">
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
              <select class="form-control" name="categoria1">
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
              <select class="form-control" name="categoria2">
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
					  <th>mês</th>
					  <th>Valor</th>
					  <!-- <th >% Salário</th>-->
					</tr>
				  </thead>
				  <!-- end cabeçalho -->
				  <tbody>
				  <c:forEach var="categoria1" items="${resultado.modeloVisao.categoria1}">
			   	    <tr>
			   		  <td> ${categoria1.categoria.descricao}</td>
			   		  <td> ${categoria1.descricao}</td>
			   		  <td> R$${categoria1.valor}</td>
			   	    </tr>
			   	  </c:forEach>
			   	  <c:forEach var="categoria2" items="${resultado.modeloVisao.categoria2}">
			   	    <tr>
			   		  <td> ${categoria2.categoria.descricao}</td>
			   		  <td> ${categoria2.descricao}</td>
			   		  <td> R$${categoria2.valor}</td>
			   	    </tr>
			   	  </c:forEach>
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
  </div> <!--  container-fluid -->
<script type="text/javascript">
	
	// Obtém o mês inicial e final do relatório.
	<% Resultado resultado = (Resultado) request.getAttribute("resultado"); 
	compararCategoriaVM cVM = new compararCategoriaVM();
	cVM = (compararCategoriaVM) resultado.getModeloVisao();
	List<String> meses = new ArrayList<String>();
	%>

	var mesesDoRelatorio = [];
	<%for(int i=0; i<cVM.getCategoria1().size(); i++){//Pega tamanho da lista da 1ª categoria
		Saida saida = (Saida) cVM.getCategoria1().get(i); //Pega o registro%>
		var aux = <%out.print("\"" + saida.getDescricao() + "\"");%>
	    mesesDoRelatorio.push(aux);//adiciona a data na lista
	<%	meses.add(saida.getDescricao());
	} 
	  for(int i=0; i<cVM.getCategoria2().size(); i++){//Pega tamanho da lista da 1ª categoria
		Saida saida = (Saida) cVM.getCategoria2().get(i); //Pega o registro%>
		var aux = <%out.print("\"" + saida.getDescricao() + "\"");%>
	    mesesDoRelatorio.push(aux);//adiciona a data na lista
	<%	meses.add(saida.getDescricao());
		}%>
	    		
    Highcharts.chart('grafico', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Comparação de categorias'
        },
        subtitle: {
            text: <% out.print("'" + cVM.getCategoria1().get(0).getCategoria().getDescricao() + " - "
            					+ cVM.getCategoria2().get(0).getCategoria().getDescricao() + "'");
            	  %>
        },
        xAxis: {
            categories: mesesDoRelatorio.sort(),
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'R$'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: <%out.print("'"+cVM.getCategoria1().get(0).getCategoria().getDescricao()+"'");
            	  %>,
            data: [<%
                   for(int i=0; i < cVM.getCategoria1().size(); i++){
                	   Saida saida = (Saida) cVM.getCategoria1().get(i);
                	   out.print(saida.getValor() + ", ");
                   }
                  %>]

        }, {
            name: <%out.print("'"+cVM.getCategoria2().get(0).getCategoria().getDescricao()+"'");
            	  %>,
            data: [<%
                   for(int i=0; i < cVM.getCategoria2().size(); i++){
                	   Saida saida = (Saida) cVM.getCategoria2().get(i);
                	   out.print(saida.getValor() + ", ");
                   }
                  %>]


        }]
    });
</script>

</body>
</html>