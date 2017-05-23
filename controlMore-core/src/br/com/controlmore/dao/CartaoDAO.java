package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;

public class CartaoDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade; //Casting
		if(cartao.getId()!=0){
			alterar(cartao);
		}else{
			String sql = "INSERT INTO Cartao (Id, Bandeira, Conta, Fecha_Dia, Vence_Dia, Limite, Limite_Utilizado) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){//Statement vai gerenciar o SQL
				//substituir todas ? pelos valores corretos
				preparador.setInt(1, cartao.getId());
				preparador.setString(2, cartao.getBandeira());
				preparador.setInt(3, cartao.getContaId());
				preparador.setInt(4, cartao.getFechaDia());
				preparador.setInt(5, cartao.getVenceDia());
				preparador.setFloat(6, cartao.getLimite());
				preparador.setFloat(7, cartao.getLimiteUtilizado());
				
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
		}// fim else
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade; // Casting
		String sql = null;
		
		//alterar bandeira
		if(cartao.getBandeira()!=null){
			sql="UPDATE Conta SET Bandeira=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setString(1,cartao.getBandeira());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar bandeira
		
		//alterar conta
		if(cartao.getContaId()!=0){
			sql="UPDATE Conta SET Conta=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setInt(1,cartao.getContaId());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar Conta
		
		//alterar dia de fechamento
		if(cartao.getFechaDia()!=0){
			sql="UPDATE Conta SET Fecha_Dia=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setInt(1,cartao.getFechaDia());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar dia de fechamento
		
		//alterar data de vencimento
		if(cartao.getVenceDia()!=0){
			sql="UPDATE Conta SET Vence_Dia=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setInt(1,cartao.getVenceDia());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar data de vencimento
		
		//alterar limite
		if(cartao.getLimite()!=0){
			sql="UPDATE Conta SET Limite=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setFloat(1,cartao.getLimite());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar Limite
		
		//alterar limite utilizado
		if(cartao.getLimiteUtilizado()!=0){
			sql="UPDATE Conta SET Limite_Utilizado=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//substituir ? pelos valores corretos
				preparador.setFloat(1,cartao.getLimiteUtilizado());
				preparador.setInt(2, cartao.getId());
				
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
		}//fim- if alterar limite utilizado 
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		String sql = "DELETE FROM Cartao WHERE id=?";
		if(cartao.getId()==0){
			return "Item não localizado!";
		}
		else{
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, cartao.getId());
				
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
		// TODO Auto-generated method stub
		return null;
	}

}
