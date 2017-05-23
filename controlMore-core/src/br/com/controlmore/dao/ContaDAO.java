package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Frequencia;

public class ContaDAO extends AbstractDAO{

	/**
	 * Método Salvar Conta
	 * @param entidade - deve receber uma instancia de conta
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String salvar(EntidadeDominio entidade) {
		Conta conta = (Conta) entidade; //Casting de entidade para conta
		if(conta.getId()!=0){
			alterar(conta);
		}else{
			String sql = "INSERT INTO Conta (Id, Banco, Tipo, Info) VALUES (?,?,?,?,?)";
			
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){//Statement vai gerenciar o SQL
				//substituir todas ? pelos valores corretos
				preparador.setInt(1, conta.getId());
				preparador.setString(2, conta.getBanco());
				preparador.setString(3,conta.getTipo());
				preparador.setString(5, conta.getInfo());
				
				//executa o SQL
				preparador.execute();
				//salva alteração no Banco
				conexao.commit();
			} catch (SQLException e) {
				try{//se der erro no try acima, vai desfazer qualquer alteração que tenha sido iniciada
					conexao.rollback();
				} catch (SQLException e1){
					return e1.toString();
				}
				return e.toString();
			}
		}//fim else
		return null;
	}

	/**
	 * Metodo Alterar Conta
	 * Pode ser alterado qualquer atributo de Conta, desde que tenha um Id.
	 * @param entidade - deve receber uma instancia de Cont
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String alterar(EntidadeDominio entidade) {
		Conta conta = (Conta) entidade; //Casting de entidade para Conta
		String sql;
		//Verifica se banco é diferente de nulo
		if(conta.getBanco()!=null){
			sql="UPDATE Conta SET Banco=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setString(1,conta.getBanco());
				preparador.setInt(2, conta.getId());
				
				//executar SQL
				preparador.execute();
				
				//salvar alteração no banco
				conexao.commit();
			} catch(SQLException e){
				try{
					//desfaz qualquer alteração feita no banco
					conexao.rollback();
				} catch(SQLException e1){
					return e1.toString();
				}
				return e.toString();
			}
		}//fim- if alterar banco
		
		//Verifica se tipo é diferente de nulo
		if(conta.getTipo()!=null){
			sql="UPDATE Conta SET Tipo=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setString(1,conta.getTipo());
				preparador.setInt(2, conta.getId());
				
				//executar SQL
				preparador.execute();
				
				//salvar alteração no banco
				conexao.commit();
			} catch(SQLException e){
				try{
					//desfaz qualquer alteração feita no banco
					conexao.rollback();
				} catch(SQLException e1){
					return e1.toString();
				}
				return e.toString();
			}
		}//fim- if alterar tipo
		
		//Verifica se Info é diferente de nulo
		if(conta.getInfo()!=null){
			sql="UPDATE Conta SET Info=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setString(1,conta.getInfo());
				preparador.setInt(2, conta.getId());
				
				//executar SQL
				preparador.execute();
				//salvar alteração no banco
				conexao.commit();
			} catch(SQLException e){
				try{
					//desfaz qualquer alteração feita no banco
					conexao.rollback();
				} catch(SQLException e1){
					return e1.toString();
				}
				return e.toString();
			}
		}//fim- if alterar info
		
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		Conta conta = (Conta) entidade;
		String sql = "DELETE FROM Conta WHERE id=?";
		if(conta.getId()==0){
			return "Item não localizado!";
		}
		else{
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, conta.getId());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Conta conta = (Conta) entidade; //Casting de Entidade para Conta
		String sql = null;
		if(conta.getBanco()==null && conta.getInfo()==null && conta.getTipo()==null	&& conta.getId()==0){
			sql="SELECT * FROM Conta";
		}
		else if(conta.getBanco()==null && conta.getInfo()==null && conta.getTipo()==null && conta.getId()!=0){
			sql="SELECT * FROM Conta WHERE Id=?";
		}
		else if(conta.getBanco()!=null && conta.getInfo()==null && conta.getTipo()==null && conta.getId()==0){
			sql="SELECT * FROM Conta WERE Banco=?";
		}
		else if(conta.getBanco()==null && conta.getInfo()==null && conta.getTipo()!=null && conta.getId()==0){
			sql="SELECT * FROM Conta WERE Tipo=?";
		}
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//Statemet que vai gerenciar o sql
			if(conta.getBanco()!=null && conta.getInfo()==null && conta.getTipo()==null && conta.getId()==0){
				//substitui o ? pelo valor correto
				preparador.setString(1, conta.getBanco());
			}
			else if(conta.getBanco()==null && conta.getInfo()==null && conta.getTipo()==null && conta.getId()!=0){
				//substitui o ? pelo valor correto
				preparador.setInt(1, conta.getId());
			}
			else if(conta.getBanco()==null && conta.getInfo()==null && conta.getTipo()!=null && conta.getId()==0){
				//substitui o ? pelo valor correto
				preparador.setString(1, conta.getTipo());
			}
			
			List<EntidadeDominio> contas = new ArrayList<EntidadeDominio>(); //Cria lista do tipo Entidade dominio
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result

			while(result.next())//enquanto houver conteudo em result
			{
				Conta c = new Conta();
				c.setId(result.getInt("Id")); //pega um Int da coluna Id e passa para Id do objeto criado
				c.setBanco(result.getString("Banco")); //pega uma String da coluna Banco e passa para Banco do objeto criado
				c.setTipo(result.getString("Tipo")); //pega uma String da coluna Tipo e passa para Tipo do objeto criado
				c.setInfo(result.getString("Info")); //pega uma String da coluna Info e passa para Info do objeto criado
				
				contas.add(c);
			}// fim while result
			return contas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
