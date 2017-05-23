package br.com.controlmore.negocio;

import br.com.controlmore.dao.RelatorioCategoriaDAO;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Saida;

public class VerificarSaldo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Saida saida = (Saida) entidade;
		if(saida.getCategoria().getId()==1){//Id 1 é o Id da Categoria Alimentação
			StringBuilder msg = new StringBuilder();
			float saldo = 0;
			float cestaBasica = 500; //esta variável deve conter o valor estipulado no questinário de perfil do usuário ou o valor médio da cesta basica;
			RelatorioCategoriaDAO relDAO = new RelatorioCategoriaDAO();
			saldo = relDAO.VerificaSaldo(saida);
			msg.append("Lembre-se para atingir sua meta e economizar,\n");
			msg.append("você deve gastar R$");
			msg.append(cestaBasica);
			msg.append(" com alimentação.\n");
			msg.append("Este mês você já gastou R$");
			msg.append((saldo + saida.getValor()));
			if(cestaBasica-(saldo+saida.getValor()) > 0){
				msg.append(". \nAinda pode gastar R$");
				msg.append(cestaBasica-(saldo+saida.getValor()));
				msg.append(".");
			}else if(cestaBasica-(saldo+saida.getValor()) < 0){
				msg.append(". \nVocê já excedeu R$");
				msg.append((saldo+saida.getValor())-cestaBasica);
				msg.append(".");
			}else if(cestaBasica-(saldo+saida.getValor()) == 0){
				msg.append(". \nVocê está no limte, ou seja, ");
				msg.append("o que gastar a partir de hoje será em excesso");
			}
				
			return msg.toString();
		}
		return null;
	}

}
