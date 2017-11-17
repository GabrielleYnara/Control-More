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
			<%if (categorias != null) {
				for (int i = 0; i < categorias.size(); i++) {
					RelatorioCategoria rel = (RelatorioCategoria) categorias.get(i);
					if (i == categorias.size() - 1) {%>
						['<%out.print(rel.getDescricao());%>', <%out.print(rel.getValorTotal());%>]
					<%} else {%>
						['<%out.print(rel.getDescricao());%>', <%out.print(rel.getValorTotal());%>],
					<%}
				}
			}%>
                
            ]
        }]
    });