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
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
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
%>

<a href="javascript:consultarE()">Grafico</a> <br>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
window.onload = grafico();
function grafico() {
    Highcharts.chart('container', {
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
        	type: 'datetime'
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
                }
            }
        },
        series: [{
            name: 'Entrada',
            data: [
                   <%if(entradas.size()>0){
                	   for(int i=0; i<entradas.size(); i++){
                		   Entrada entrada = entradas.get(i);
                		   if(i==entradas.size()-1){//ultimo item %>
                			   [Date.UTC(<%entrada.getDataEntrada().getYear();%>,
                			   <%entrada.getDataEntrada().getMonth();%>,
                			   <%entrada.getDataEntrada().getDay();%>),
                			   <%entrada.getValor();%>]
                		   <%}else{//não é o tulrimo item %>
                			   [Date.UTC(<%entrada.getDataEntrada().getYear();%>,
                			   <%entrada.getDataEntrada().getMonth();%>,
                			   <%entrada.getDataEntrada().getDay();%>),
                			   <%entrada.getValor();%>],
                		  <% }
                	   }
                   }%>
                   ]
        }, {
            name: 'London',
            data: [<%if(saidas.size()>0){
				   for(int i=0; i<saidas.size(); i++){
					   Saida saida = saidas.get(i);
					   if(i==saidas.size()-1){//ultimo item %>
						   [Date.UTC(<%saida.getData().getYear();%>,
						   <%saida.getData().getMonth();%>,
						   <%saida.getData().getDay();%>),
						   <%saida.getValor();%>]
					   <%}else{//não é o tulrimo item %>
						   [Date.UTC(<%saida.getData().getYear();%>,
						   <%saida.getData().getMonth();%>,
						   <%saida.getData().getDay();%>),
						   <%saida.getValor();%>],
					  <% }
				   }
				}%>]
        }]
    });
};
</script>
</div>
</body>
</html>