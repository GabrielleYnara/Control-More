package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.aPagarVM;

public class aPagarDAO extends AbstractDAO{

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
	
	public aPagarVM contasCartoes(){
		aPagarVM aPagarVM = new aPagarVM();
		String sql = "SELECT Id, "
				   + "		 Tipo, "
				   + "       Banco, "
				   + "       'conta' AS TP "
				   + "  FROM Conta "
				   + " UNION ALL "
				   + "SELECT CA.Id, "
				   + "       CA.Bandeira, "
				   + "       CO.Banco, "
				   + "       'cartao' AS TP "
				   + "  FROM Cartao CA "
				   + " INNER JOIN Conta CO ON CO.Id=CA.Conta_Pagto";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Conta c = new Conta();

				c.setId(result.getInt("Id"));//Id
				c.setTipo(result.getString("Tipo"));//Bandeira ou tipo de conta
				c.setBanco(result.getString("Banco"));//Banco
				c.setInfo(result.getString("TP"));//Conta ou Cartão
				aPagarVM.setContasCartoes(c);
			}
			 
			return aPagarVM;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return aPagarVM;
	}
}
