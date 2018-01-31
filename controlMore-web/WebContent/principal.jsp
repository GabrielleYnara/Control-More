<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.LocalDate"%>
<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%@page import="br.com.controlmore.vm.ResumoVM"%>

<%//@page import="br.com.controlmore.dominio.Conta"%>
<%//@page import="br.com.controlmore.dominio.Frequencia"%>
<%//@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom styles -->
<link rel="stylesheet" href="css/principal.css">
<link rel="stylesheet" href="css/HighCharts.css">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Taglib para formatação de datas -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- HighCharts -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<title>ControlMore</title>
    
<script type="text/javascript">
    function indisponivel(){
    	alert("Sinto muito! \n"
    		 +"Esta funcionalidade ainda não está disponível!");
    }
</script>

</head>
<body>
<c:import url="menu.jsp" />

  <div class="container-fluid" >
  <% request.getSession().setAttribute("saida", null); %>
  <% request.getSession().setAttribute("entrada", null); %>

	<h4>Bem vindo(a) ${usuario.nome}</h4>
	
	<div class="col-sm-5 col-md-3">
	  <div class="bs-callout bs-callout-info" > <!-- Callout Saldo -->
	  	<div align="center">
	      <h4> R$ ${resultado.modeloVisao.saldo}</h4>
	  	  <p data-toggle="tooltip" data-placement="right" title="Saldo de todas as contas">Saldo Geral</p>
	  	</div>
	  </div> <!-- Fim Callout Saldo -->
  	  
  	  <div class="bs-callout bs-callout-warning">
	  	Programe-se para viver com 70% da sua renda mensal.
		<ul>
		  <li>55% para gastos essenciais.</li>
		  <li>5% para gastos com educação.</li>
		  <li>10% para gastos diversos.</li>
		</ul>
		Reserve 30% da sua renda mensal para projetos a longo prazo.
	  </div> <!-- class="bs-callout bs-callout-info" -->
	  
	  <!-- Botão Registrar Entrada  -->
  	  <div class="col-md-12">
  	    <a type="button" class="btn btn-success btn-lg col-xs-12" id="espaco" 
  	    	href="javascript:location.href='Home?acao=contaReceber';">
  	      Registrar Entrada <span class="glyphicon glyphicon-arrow-up"></span>
  	    </a>
  	  </div><!-- Fim Botão Registrar Entrada  -->
  	  
  	  <!-- Botão Registrar Saida  -->
  	  <div class="col-md-12">
  	    <a type="button" class="btn btn-warning btn-lg col-xs-12" id="espaco"  
			href="javascript:location.href='Home?acao=contaPagar';">
  	      Registrar Saida <span class="glyphicon glyphicon-arrow-down"></span>
  	    </a>
  	  </div><!-- Fim Botão Registrar Saida  -->
	</div> <!-- calss="col-sm-4 col-md-3" -->
  
	<div class="painel col-xs-12 col-sm-7 col-md-6"><!-- Resumo Financeiro -->
	  <div class="panel panel-info"> <!-- Painel -->
		<div class="panel-heading text-center">
		  Resumo financeiro
		</div>
		<div class="panel-body form-group">
		  <form action="" method="POST">
  			<div id="graficoMensal">
  			<!-- Vai exibir o grafico aqui -->
  			</div>
			<div align="right">
			  <p>Saldo Geral <saldo>R$ ${resultado.modeloVisao.saldo}</saldo></p>
			</div><!-- end right -->
			<ul>
			  <li>
			    <i class="glyphicon glyphicon-piggy-bank"></i><p><strong>Carteira</strong> R$0,00</p>
			    <p>Outros</p>
			  </li><!-- end item lista -->
			  <li>
			    <i class="glyphicon glyphicon-picture"></i><p><strong>Itaú</strong> R$0,00</p>
			    <p>Conta Corrente</p>
			  </li><!-- end item lista -->
			  <li>
			  	<i class="glyphicon glyphicon-picture"></i><p><strong>Itaú</strong> R$0,00</p>
			  	<p>Conta Poupança</p>
			  </li><!-- end item lista -->
			</ul><!-- end lista bancos -->
		  </form>
		</div><!-- painel body -->
	  </div> <!-- painel -->
	</div><!-- Resumo Financeiro -->
		
	<div class="col-xs-12 col-sm-6 col-md-3"><!-- Painel de Contas a Pagar -->
	  <div class="panel panel-info"> <!-- Painel  -->
		<div class="panel-heading text-center">
		  Contas à Pagar
		</div>
		<div class="panel-body form-group">
		  <form action="" method="POST">
		    <h5>Contas Atrasadas <i class="glyphicon glyphicon-thumbs-down"></i></h5>
		    <table class="col-md-12">
		      <tbody>
		      	<c:if test="${empty resultado.modeloVisao.aPagarVencida}">
		      	  <tr>
		    		<td align="center"> Nenhuma conta atrasada.</td>
		    	  </tr>
		      	</c:if>
		    	<c:forEach var="aPagarVencida" items="${resultado.modeloVisao.aPagarVencida}">
		    	  <tr>
		    		<td><a href="javascript:location.href='Saida?acao=consultar&txtId=${aPagarVencida.id}';"> &bull;	&nbsp;	${aPagarVencida.descricao}</a></td>
		    		<td class="text-right"><fmt:formatDate value="${aPagarVencida.data}" pattern="dd/MM/YY"/></td>
		    		<td class="text-right"> R$${aPagarVencida.valor}</td>
		    	  </tr>
		    	</c:forEach>	
		      </tbody>
		    </table><!-- table contas atrasadas -->
		    		
		    <h5>Próximas <spam class="glyphicon glyphicon-thumbs-down"></spam></h5>
		    <table class="col-md-12">
		      <tbody>
		      	<c:if test="${empty resultado.modeloVisao.aPagar}">
		      	  <tr>
		    		<td align="center"> Nenhuma conta nos próximos 10 dias.</td>
		    	  </tr>
		      	</c:if>
		    	<c:forEach var="aPagar" items="${resultado.modeloVisao.aPagar}">
		    	  <tr>
		    		<td> &bull;	&nbsp;	${aPagar.descricao}</td>
		    		<td class="text-right"> <fmt:formatDate value="${aPagar.data}" pattern="DD/MM"/></td>
		    	  	<td class="text-right"> R$${aPagar.valor}</td>
		    	  </tr>
		    	</c:forEach>	
		      </tbody>
		    </table>
		  </form>
		</div><!-- painel body -->
	  </div><!-- end painel -->
	</div><!-- end painel de Contas a Pagar -->
	
	<div class="col-xs-12 col-sm-6 col-md-3"><!-- Painel de Contas a Receber -->
	  <div class="panel panel-info"> <!-- Painel Saída Simples -->
		<div class="panel-heading text-center">
		  Contas à Receber
		</div>
		<div class="panel-body form-group">
		  <form action="" method="POST">
			<h5>Recebimentos Atrasadas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
			<table class="col-md-12">
			  <tbody>
			  	<c:if test="${empty resultado.modeloVisao.aReceberAtrasada}">
			  	  <tr>
			  	  	<td align="center">Nenhum recebimento atrasado.</td>
			  	  </tr>
			  	</c:if>
			    <c:forEach var="aReceberAtrasada" items="${resultado.modeloVisao.aReceberAtrasada}">
			      <tr>
			    	<td><a href="javascript:location.href='Entrada?acao=consultar&txtId=${aReceberAtrasada.id}';"> &bull;	&nbsp;	${aReceberAtrasada.descricao}</a></td>
			    	<td class="text-right"> <fmt:formatDate value="${aReceberAtrasada.dataEntrada}" pattern="dd/MM/YY"/></td>
			    	<td class="text-right"> R$${aReceberAtrasada.valor}</td>
			      </tr>
			    </c:forEach>	
			  </tbody>
			</table>
				<br>
			<h5>Próximas <spam class="glyphicon glyphicon-thumbs-up"></spam></h5>
			<table class="col-md-12">
			  <tbody>
			    <c:if test="${empty resultado.modeloVisao.aReceber}">
			  	  <tr>
			  	  	<td align="center">Nenhum recebimento nos proximos 10 dias.</td>
			  	  </tr>

			  	</c:if>
			    <c:forEach var="aReceber" items="${resultado.modeloVisao.aReceber}">
			   	  <tr>
			   		<td> &bull;	&nbsp;	${aReceber.descricao}</td>
			   		<td class="text-right"> <fmt:formatDate value="${aReceber.dataEntrada}" pattern="dd/MM"/></td>
			   		<td class="text-right"> R$${aReceber.valor}</td>
			   	  </tr>
			   	</c:forEach>	
			  </tbody>
			</table>
		  </form>
		</div><!-- painel body -->
	  </div><!-- painel -->
	</div><!-- end painel de contas a receber -->
</div> <!--  container-fluid -->

<%--TROCAR PARA INCLUDE. 
<script src="js/graficoMensal.js"></script>--%>
<script type="text/javascript">
	<% Resultado resultado = (Resultado) request.getAttribute("resultado"); 
		ResumoVM rVM = new ResumoVM();
		rVM = (ResumoVM) resultado.getModeloVisao();
	%>
	Highcharts.chart('graficoMensal',{
    chart: {
        type: 'line'
    },
    title: {
        text: '<% 
        		LocalDate data = LocalDate.now();
        		out.print("Gasto Mensal " + data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")));
        	 %>'
    },
    subtitle: {
        text: 'Contas a pagar e receber'
    },
    xAxis: {//Dias
    	 categories: [<%
    	 				if(rVM.getDias().size()>0){
    	 					for(int i=0; i<rVM.getDias().size(); i++){
  		   		   				out.print(rVM.getDias().get(i)+ ", ");
		   					}
	   					}
    	 			%>]
    				
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
            data:[<%
            		if(rVM.getcRecebidas().size()>0){
						for(int i=0; i<rVM.getcRecebidas().size(); i++){
	 		   				out.print(rVM.getcRecebidas().get(i)+ ", ");
						}
					}
            	 %>]
        		},
        	{name: 'A Pagar',
            data:[<% 
		            if(rVM.getcPagas().size()>0){
						for(int i=0; i<rVM.getcPagas().size(); i++){
				   				out.print(rVM.getcPagas().get(i)+ ", ");
						}
					}
            	 %>]
        	}]
	});
</script>
</body>
</html>