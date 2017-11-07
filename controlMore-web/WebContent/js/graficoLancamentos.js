//<!-- Import da taglib pra uso de jstl -->
//<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
/*
 * Sugestão: colocar a execução do gráfico em uma fuction que recebe uma lista de Entradas e Saidas
 * - na pagina principal, executar a View Helper de Filtro (Filtro?acao=consultar&txtConsulta=EntradaSaidaMensal) 
 * ao carregar a tela.
 * - usar JSTL para receber os dados da view e passar a lista para a function grafilMensal();
 */
	var date = new Date();   
	var ultimoDia = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	var dias = [1,5,10,15,20,15,30];
	/*for (i = 0; i<ultimoDia.getDate(); i++){
	   	dias.push(i+1);
	}*/

    Highcharts.chart('graficoMensal',{
        chart: {
            type: 'line'
        },
        title: {
            text: 'Gasto mensal'
        },
        subtitle: {
            text: 'Contas a pagar e receber'
        },
        xAxis: {
        	 categories: dias
        				
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
	            data:[20,40,75,87,98,12,14,21,02]
	        		},
	        	{name: 'A Pagar',
	            data:[15,37,84,72,76,85,25,82]
	        	}]
    });
