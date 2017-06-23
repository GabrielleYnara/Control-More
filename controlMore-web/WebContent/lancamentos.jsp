<%@page import="java.time.temporal.TemporalAdjusters"%>
<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%@page import="br.com.controlmore.dominio.Entrada"%>
<%@page import="br.com.controlmore.dominio.Saida"%>
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

<!-- Custom styles -->
<link rel="stylesheet" href="css/lancamentos.css">

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<title>Lançamentos</title>
</head>
<body>
<%@include file="menu.jsp"%>

<script type="text/javascript">
// A $( document ).ready() block.

function consultarE(id){
	location.href="Entrada?acao=consultar&txtId="+ id;
}
function excluirE(id){
	location.href="Entrada?acao=excluir&txtId="+ id;
}
function consultarS(id){
	location.href="Saida?acao=consultar&txtId="+ id;
}
function excluirS(id){
	location.href="Saida?acao=excluir&txtId="+ id;
}
function listarE(){
	location.href="Entrada?acao=consultar";
}
function listarS(){
	location.href="Saida?acao=consultar";
}
</script>
<% 
	List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
	List<Entrada> entradas = new ArrayList<Entrada>();
	if(request.getSession().getAttribute("entrada")!=null){
		resultado = (Resultado) request.getSession().getAttribute("entrada");
		entidades.clear();
		entidades = resultado.getEntidades();
		for(EntidadeDominio entidade : entidades){
			entradas.add((Entrada) entidade);
		}
	}
	entidades.clear();
	List<Saida> saidas = new ArrayList<Saida>();
	if(request.getSession().getAttribute("saida")!=null){
		resultado = (Resultado) request.getSession().getAttribute("saida");
		entidades = resultado.getEntidades();
		for(EntidadeDominio entidade : entidades){
			saidas.add((Saida) entidade);
		}
	}
	entidades.clear();
	List<EntidadeDominio> entradaSaida = new ArrayList<EntidadeDominio>();
	if(request.getAttribute("filtro")!=null){
		resultado= (Resultado) request.getAttribute("filtro");
		entradaSaida = resultado.getEntidades();
	}
%>

<script type="text/javascript">
function grafico() {
	var meses = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
	                'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
	
	// Obtém o mês inicial e final do relatório.
	<%if(entradaSaida.size()>0){
		int mesInicial = 12;
		int mesFinal = 1;
 	   	for(int i=0; i<entradaSaida.size(); i++){
 		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Entrada")){ 
 			   Entrada entrada = (Entrada) entradaSaida.get(i);
     		   if(Integer.valueOf(entrada.getDescricao()) < mesInicial) {
     			   mesInicial = Integer.valueOf(entrada.getDescricao());
     		   }
     		  	if(Integer.valueOf(entrada.getDescricao()) > mesFinal) {
    			   mesFinal = Integer.valueOf(entrada.getDescricao());
    		   }
 		   }
 	   }
 	   out.println("var mesInicial = " + mesInicial + ";");
 	   out.println("var mesFinal = " + mesFinal + ";");
    }%>
    
	var mesesDoRelatorio = [];
	console.log(mesInicial + "-" + mesFinal);
	for(i = mesInicial; i <= mesFinal; i++) {
		if(i > 12) {
			i = 1;
		}
		mesesDoRelatorio.push(meses[i-1]);
	}
	
    Highcharts.chart('container',{
        chart: {
            type: 'line'
        },
        title: {
            text: 'Gasto anual'
        },
        subtitle: {
            text: 'Contas a pagar e receber'
        },
        xAxis: {
        	 categories: mesesDoRelatorio
        				
        },
        yAxis: {
            title: {
                text: 'R$'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        series: [{
            name: 'A Receber',
            data:[
                   <%if(entradaSaida.size()>0){
                	   for(int i=0; i<entradaSaida.size(); i++){
                		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Entrada")){ 
                			   Entrada entrada = (Entrada) entradaSaida.get(i);
	                		   out.print(entrada.getValor() + ", ");
                		   }
                	   }
                   }%>
                   ]
        }, {name: 'A Pagar',
            data:[
                  <%if(entradaSaida.size()>0){
               	   for(int i=0; i<entradaSaida.size(); i++){
               		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Saida")){ 
               			   Saida saida = (Saida) entradaSaida.get(i);
	                		   out.print(saida.getValor() + ", ");
               		   }
               	   }
                  }%>
                 ]
           }
        ]
    });
};
</script>
<div id="container" ></div>
<br><br>
<div align="center" >
	<form action="Filtro?acao=consultar&txtConsulta=EntradaSaida" method="post">
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
<br>
<div class="col-md-6" align="center">
	<form action="Entrada?acao=consultar" method="POST">
		<button type="submit" class="btn btn-success">Listagem de contas a receber</button>
	</form>
</div>
<div class="col-md-6" align="center">
	<form action="Saida?acao=consultar" method="POST">
		<button type="submit" class="btn btn-warning">Listagem de contas a pagar</button>
	</form>
</div>
<!-- <div class="col-md-6" >
  <label class="col-md-2">Data Inicial</label>
  <input type="date" id="txtDtInicial" class=" col-md-3" required autofocus>

  <label class="col-md-2">Data Final</label>
  <input type="date" id="txtDtFinal" class=" col-md-3" required autofocus>
</div><!-- end div 

<button class="btn btn-default col-md-1" id="btnFiltrar" type="submit" >Filtrar</button>-->
<% if(entradas.size()>0){%>
	<table class="table table-striped">
	  <thead>
	  	<caption><h3>Contas a receber</h3></caption>
	    <tr>
	      <th>Descrição</th>
	      <th>Situação</th>
	      <th>Tipo</th>
	      <th>Data</th>
	      <th>Valor</th>
	      <th>Ação</th>
	    </tr><!-- end linha -->
	  </thead><!-- end cabeçalho -->
	  <tbody>
	<%for(Entrada entrada : entradas){%>			
		<tr><!-- Linha -->
		<!-- Colunas -->
			<td><%out.print(entrada.getDescricao());%></td>
			<td><%out.print(entrada.getSituacao());%></td>
			<td>Entrada</td>
			<td><%out.print(entrada.getDataEntrada());%></td>
			<td>R$<%out.print(entrada.getValor());%></td>
			<td>
				<a href="javascript:consultarE(<%out.print(entrada.getId());%>);"><span class="glyphicon glyphicon-pencil"></span></a>
	      		<a href="javascript:excluirE(<%out.print(entrada.getId());%>);"><span class="glyphicon glyphicon-trash"></span></a>
			</td>
		</tr><!-- end linha -->
	<%}//end for entradas 
} //end if%> 
		
<% if(saidas.size()>0){%>
	<table class="table table-striped" sumary="Contas a receber">
	  <thead>
	  	<caption><h3>Contas a pagar</h3></caption>
	    <tr>
	      <th>Descrição</th>
	      <th>Situação</th>
	      <th>Tipo</th>
	      <th>Data</th>
	      <th>Valor</th>
	      <th>Ação</th>
	    </tr><!-- end linha -->
	  </thead><!-- end cabeçalho -->
	  <tbody>			
	<%for(Saida saida : saidas){%>
		<tr><!-- Linha -->
			<!-- Colunas -->
			<td><%out.print(saida.getDescricao());%></td>
			<td><%out.print(saida.getSituacao());%></td>
			<td>Saida</td>
			<td><%out.print(saida.getData());%></td>
			<td>R$<%out.print(saida.getValor());%></td>
			<td>
				<a href="javascript:consultarS(<%out.print(saida.getId());%>);"><span class="glyphicon glyphicon-pencil"></span></a>
	      		<a href="javascript:excluirS(<%out.print(saida.getId());%>);"><span class="glyphicon glyphicon-trash"></span></a>
			</td>
		</tr><!-- end linha -->
	<%}//end for saidas 
}// end if%> 
 </tbody><!-- end corpo -->
</table><!-- end table -->

<script type="text/javascript">
window.load = grafico();

</script>
</body>
</html>