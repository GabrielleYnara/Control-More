package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.aPagarVM;
import br.com.controlmore.vm.upLoadVM;

public class upLoadDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		upLoadVM upLoad = (upLoadVM) entidade;
		List<Saida> saidas = new ArrayList<Saida>();
		List<Entrada> entradas = new ArrayList<Entrada>();
		
		saidas.addAll(upLoad.getSaidas());
		entradas.addAll(upLoad.getEntradas());
		//Consultar no banco quais as categorias cadastradas
		List<Categoria> categorias = new ArrayList<Categoria>();
		CategoriaDAO cDAO = new CategoriaDAO();
		Categoria categoria = new Categoria();
		List<EntidadeDominio> catEntidade = new ArrayList<EntidadeDominio>(cDAO.consultar(categoria));
		for(int i =0; i<catEntidade.size(); i++){
			categorias.add((Categoria) catEntidade.get(i));
		}
		
		//Consutar no banco quais as contas e cartões cadastrados
		aPagarDAO aPagarDAO = new aPagarDAO();
		aPagarVM aPagarVM = aPagarDAO.contasCartoes();
		List<Conta> contasCartoes = new ArrayList<Conta>();
		contasCartoes.addAll(aPagarVM.getContasCartoes());
		//Verificar registros de contas a pagar 
		for(int i =0; i<saidas.size();i++){
			for(int j=0; j<contasCartoes.size();j++){
				//Recebe tipo - banco, porque é exibido assim para o usuário e dessa forma que ele vai inserir na planilha de importação
				String aux = contasCartoes.get(j).getTipo() + " - " + contasCartoes.get(j).getBanco();
				if(!saidas.get(i).getConta().getInfo().isEmpty() && saidas.get(i).getConta().getInfo().equalsIgnoreCase(aux)){//Seo registro de conta for igual ao registro do banco
					//Identifica se é conta ou cartão e salva o id correspondente
					if(contasCartoes.get(j).getInfo().equals("conta")){
						saidas.get(i).getConta().setId(contasCartoes.get(j).getId());
						saidas.get(i).getCartao().setId(0);
					}//if conta
					
					if(contasCartoes.get(j).getInfo().equals("cartao")){

						saidas.get(i).getCartao().setId(contasCartoes.get(j).getId());
						saidas.get(i).getConta().setId(0);
					}//if cartao 
					
					//Verifica a categoria - descricao
					for(int k =0; k<categorias.size(); k++){
						aux=categorias.get(k).getDescricao();
						String cat = saidas.get(i).getCategoria().getDescricao();
						if(cat!=null && cat.equalsIgnoreCase(aux)){
							saidas.get(i).getCategoria().setId(categorias.get(k).getId());
							k = categorias.size();
						}//if categoria=aux
					}//for categorias
					
					//Montar Insert de todos os registros
					StringBuilder sql = new StringBuilder();
					
					if(saidas.get(i).getConta().getId() !=0 ){
						sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
						sql.append("Frequencia, Categoria, Conta, DataSaida) ");
						sql.append("VALUES (?,?,?,'PENDENTE',1,1,?,?,?) ");
					}// if conta id !=0
					if(saidas.get(i).getCartao().getId() !=0 ){
						sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
						sql.append("Frequencia, Categoria, Cartao, DataSaida) ");
						sql.append("VALUES (?,?,?,'PENDENTE',1,1,?,?,?) ");
					}//if cartao id != 0
					
					try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
						//substitue as ? pelos "valores" que compõe o objeto
						
						preparador.setString(1, saidas.get(i).getDescricao());
						preparador.setFloat(2, saidas.get(i).getValor());
						Timestamp time = new Timestamp(saidas.get(i).getDtCadastro().getTime());
						preparador.setTimestamp(3, time);
						preparador.setInt(4, saidas.get(i).getCategoria().getId());
						if(saidas.get(i).getConta().getId() !=0)
							preparador.setInt(5, saidas.get(i).getConta().getId());
						if(saidas.get(i).getCartao().getId() !=0)
							preparador.setInt(5, saidas.get(i).getCartao().getId());
						preparador.setDate(6, new java.sql.Date(saidas.get(i).getData().getTime()) );
						//executar comando
						preparador.execute();
				
					}catch (SQLException e) {
						e.printStackTrace();
					}//catch - fim	
					j=contasCartoes.size();
				}// if encontrou conta
			}// for contas e cartoes
		}//for saidas
		
		//Verificar registros de contas a receber
		for(int i =0; i<entradas.size();i++){
			for(int j=0; j<contasCartoes.size();j++){
				//Recebe tipo - banco, porque é exibido assim para o usuário e dessa forma que ele vai inserir na planilha de importação
				String aux = contasCartoes.get(j).getTipo()+" - "+contasCartoes.get(j).getBanco();
				if(!entradas.get(i).getConta().getInfo().isEmpty() && entradas.get(i).getConta().getInfo().equals(aux)){//Seo registro de conta for igual ao registro do banco
					//Identifica se é conta ou cartão e salva o id correspondente
					if(contasCartoes.get(j).getInfo().equals("conta")){
						entradas.get(i).getConta().setId(contasCartoes.get(j).getId());
						entradas.get(i).getCartao().setId(0);
					}//if conta
					if(contasCartoes.get(j).getInfo().equals("cartão")){
						entradas.get(i).getCartao().setId(contasCartoes.get(j).getId());
						entradas.get(i).getConta().setId(0);
					}//if cartao 
					String sql = null;
					if(entradas.get(i).getConta().getId()!=0){
						sql="INSERT INTO Entrada (Descricao, Valor, DataCadastro, Frequencia, Conta, DataEntrada, Situacao) VALUES (?,?,?,1,?,?,'PENDENTE')";
					}//if conta
					if(entradas.get(i).getCartao().getId()!=0){
						sql="INSERT INTO Entrada (Descricao, Valor, DataCadastro, Frequencia, Cartao, DataEntrada, Situacao) VALUES (?,?,?,1,?,?,'PENDENTE')";
					}//if cartao
					try(PreparedStatement preparador = conexao.prepareStatement(sql))
					{
						//substituir os ? pelos valores
						preparador.setString(1, entradas.get(i).getDescricao());
						preparador.setFloat(2, entradas.get(i).getValor());
						preparador.setDate(3, new java.sql.Date(entradas.get(i).getDtCadastro().getTime()));
						if(entradas.get(i).getCartao().getId()!=0)
							preparador.setInt(4, entradas.get(i).getCartao().getId());
						if(entradas.get(i).getConta().getId()!=0)
							preparador.setInt(4, entradas.get(i).getConta().getId());
						preparador.setDate(5, new java.sql.Date(entradas.get(i).getDataEntrada().getTime()));

						// executar o comando no banco
						preparador.execute();					
					} catch (SQLException e) {
						
						e.printStackTrace();			
					}//catch
					j = contasCartoes.size();
				}//if encontrou conta ou cartao
			}//for contasCartoes
		}//for registros de contas a receber
		
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

}
