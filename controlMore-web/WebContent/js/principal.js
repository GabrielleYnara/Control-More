/*
 * 
 */
function filtro(){
	var now = new Date();
	var primeiroDia = new Date(date.getFullYear(), date.getMonth(), 1);
	var ultimoDia = new Date(date.getFullYear(), date.getMonth() + 1, 0);

	console.log(primeiroDia, ultimoDia);
	location.href="Filtro?acao=consultar&txtConsulta=EntradaSaida&";
}

BigDecimal TtlEntrada = 0;
BigDecimal TtlSaida = 0; 

//faz consulta no banco
String sql = "SELECT SUM(S.Valor) "
		   + "FROM Saida S "
		   + "UNION "
		   + "SELECT SUM(Valor) "
		   + "FROM Entrada";
CachedRowSetImpl() crs = 
//atribui resultado às variáveis;

//envair resultado para view (Saldo)
SaldoGeral = TtlSaida-TtlEntrada;