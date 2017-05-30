<%@page import="br.com.controlmore.dominio.RelatorioCategoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/graficoCat.css" rel="stylesheet">

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>


<title>Control More</title>
</head>
<body>
<%@include file="menu.jsp"%>
<%
	List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
	RelatorioCategoria categoria = new RelatorioCategoria();
	if(request.getSession().getAttribute("relCat") !=null){		
		resultado = (Resultado) request.getSession().getAttribute("relCat");
		categorias = resultado.getEntidades();
	}
%>

<script type="text/javascript">
function grafico() {
    Highcharts.chart('grafico', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: 'Resumo de seus gastos por categoria'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'do total gasto',
            colorByPoint: true,
            data: [
			<%
			if(categorias!=null){
				for (int i =0; i < categorias.size(); i ++) {
					RelatorioCategoria rel = (RelatorioCategoria) categorias.get(i);
					if(i== categorias.size()-1) {%>
						['<% out.print(rel.getDescricao());%>', <%out.print(rel.getValorTotal());%>]
					<%} else {%>
						['<% out.print(rel.getDescricao());%>', <%out.print(rel.getValorTotal());%>],
					<%}
				}
			}%>
                
            ]
        }]
    });
};
</script>

<div class="row" align="center">
	<form action="RelCat?acao=consultar" method="post">
		<div class="col-md-1">
			Data Inicial
		</div>
		<div class="col-md-2">
			<input type="date" name="txtDataInicial" class="form-control" data-toggle="tooltip" title="Início" required autofocus><br>
		</div>
		<div class="col-md-1">
			Data Final
		</div>
		<div class="col-md-2">
			<input type="date" name="txtDataFinal" class="form-control" data-toggle="tooltip" title="Início" required autofocus><br>
		</div>
		<div class="col-md-1">
			  <button class="btn btn-info btn-block" type="submit" name="acao" value="filtrar" placeholder="Filtrar">Filtrar</button>
		</div>
	</form>
</div>
<div class="row">
	<div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Resumo por categorias 
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					<table class="table table-hover">
			 			<thead><!-- cabeçalho -->
					     	<tr><!-- linha -->
					     		<!-- colunas -->
					        	<th >Categoria</th>
					        	<th >Gastos</th>
					        	<!-- <th >% Salário</th>-->
					      	</tr>
					    </thead><!-- end cabeçalho -->
					    <tbody><!-- corpo da tabela -->
					      	
					    	<!-- colunas -->
					   		<% if(categorias!=null){
								for(EntidadeDominio entidade : categorias){
									RelatorioCategoria rel = (RelatorioCategoria) entidade;%>
									<tr><!-- Linha -->
										<!-- Colunas -->
										<td><%out.print(rel.getDescricao());%></td>
										<td>R$<%out.print(rel.getValorTotal());%></td>
									</tr><!-- end linha -->
							<%	}//end for entradas 
							} //end if%> 
						</tbody>
					</table>
					<div class="row" align="center" hidden>
						<button type='submit' class="btn btn-default" id='acao'><a href="projecaoEconomia.jsp">Detalhes</a></button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="grafico"> </div>
</div>

<script type="text/javascript">
	window.load = grafico();
</script>

</body>
</html>