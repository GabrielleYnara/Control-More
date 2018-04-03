package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Juros;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.AnaliseVM;
import br.com.controlmore.vm.ResumoVM;

public class AnaliseDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public AnaliseVM aPagarVencidas(){
		AnaliseVM aVM = new AnaliseVM();
		String sql = "SELECT S.Id,"
					+"       S.Valor, "
					+"       S.Descricao, "
					+"       S.DataSaida, "
					+"       S.Importancia, "
					+"		 S.Categoria AS idCategoria, "
					+"       C.Descricao As Categoria,"
					+"       J.Juros, "
					+"       J.Multa, "
					+"       (S.Valor+J.Juros+J.Multa) AS Total "
					+"  FROM Saida S "
					+"  LEFT JOIN Juros J ON s.Juros= J.Id "
					+"  LEFT JOIN Categoria C ON S.Categoria = C.Id "
					+" WHERE S.DataSaida < (SELECT SYSDATE FROM Dual) "
					+"   AND UPPER(S.Situacao) <> 'PAGO' "
					+" ORDER BY S.Importancia DESC, Total DESC, S.Valor DESC, S.DataSaida ASC";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Saida s = new Saida();
				Juros j = new Juros();
				Categoria c = new Categoria();
				
				s.setId(result.getInt("Id"));
				s.setValor(result.getFloat("Valor"));
				s.setDescricao(result.getString("Descricao"));
				s.setData(result.getDate("DataSaida"));
				s.setImportancia(result.getInt("Importancia"));
				j.setJuros(result.getFloat("Juros"));
				j.setMulta(result.getFloat("Multa"));
				s.setJuros(j);
				c.setId(result.getInt("idCategoria"));
				c.setDescricao(result.getString("Categoria"));
				c.setCategoria(c);
				
				aVM.setaPagar(s);
			}
			return aVM;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
