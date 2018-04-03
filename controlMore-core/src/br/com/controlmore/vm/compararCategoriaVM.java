package br.com.controlmore.vm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.viewmodel.IViewModel;
/**
 * Será responsável por receber uma lista de todas as categorias cadastradas no banco
 * uma lista com os valores e data de cada categoria que estiver sendo camparada (duas)
 * @author Gabrielle Y
 *
 */
public class compararCategoriaVM implements IViewModel{
	//Para receber a lista de todas as categorias cadastradas
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	//Para receber os valores e datas da categoria1 de comparação
	//Id receberá o Id da categoria
	//valor irá receber o valor total do mês
	//descricao irá receber o mês atual 
	private List<Saida> categoria1 = new ArrayList<Saida>();
	private int idCategoria1;
	private int idCategoria2;
	
	//Para receber os valores e datas da categoria2 de comparação
	private List<Saida> categoria2 = new ArrayList<Saida>();
	
	//Para receber as datas do filtro
	private Date dtInicial;
	private Date dtFinal;
	
	public List<Categoria> getCategorias(){
		return categorias;
	}
	public void setCategorias(Categoria c){
		this.categorias.add(c);
	}
	public List<Saida> getCategoria1(){
		return categoria1;
	}
	public void setCategoria1(Saida s){
		this.categoria1.add(s);
	}
	public List<Saida> getCategoria2(){
		return categoria2;
	}
	public void setCategoria2(Saida s){
		this.categoria2.add(s);
	}
	public Date getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}
	public Date getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}
	public int getIdCategoria1() {
		return idCategoria1;
	}
	public void setIdCategoria1(int idCategoria1) {
		this.idCategoria1 = idCategoria1;
	}
	public int getIdCategoria2() {
		return idCategoria2;
	}
	public void setIdCategoria2(int idCategoria2) {
		this.idCategoria2 = idCategoria2;
	}

	
	
	
	

}
