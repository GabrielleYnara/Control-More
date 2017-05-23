package br.com.controlmore.negocio;

import java.text.DecimalFormat;
import java.util.List;

import br.com.controlmore.dominio.AvaliacaoGasto;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Saida;

public class Avaliacao implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		AvaliacaoGasto avaliacao = (AvaliacaoGasto) entidade;
		List<Saida> saidas = avaliacao.getSaidas();
		StringBuilder msg = new StringBuilder();
		float porcentagem =0 ;
		float essencial=0;
		float aux = 0;
		DecimalFormat df = new DecimalFormat("0.0");
		msg.append("De acordo com o questionário que você respondeu ao se cadastrar, ");
		msg.append("Você tem uma renda de R$");
		msg.append(avaliacao.getQuestionario().getRenda());
		msg.append("\nE nos informou que gasta mensalmente: R$");
		msg.append(avaliacao.getQuestionario().getCompra());
		msg.append(" na compra com alimentação, R$");
		msg.append(avaliacao.getQuestionario().getAgua());
		msg.append(" com a conta de agua, R$");
		msg.append(avaliacao.getQuestionario().getLuz());
		msg.append(" com a conta de luz, R$");
		msg.append(avaliacao.getQuestionario().getInternet());
		msg.append(" com internet, TV a cabo e afins, R$");
		msg.append(avaliacao.getQuestionario().getAluguel());
		msg.append(" com aluguel, R$");
		msg.append(avaliacao.getQuestionario().getRecarga());
		msg.append(" com recarga de celular.\n");
		msg.append("Para nós esses gastos são categorizados como essenciais, e eles representam ");
		essencial = avaliacao.getQuestionario().getCompra() + avaliacao.getQuestionario().getAgua()
				+ avaliacao.getQuestionario().getLuz() + avaliacao.getQuestionario().getInternet()
				+ avaliacao.getQuestionario().getAluguel() + avaliacao.getQuestionario().getRecarga();
		porcentagem = (essencial * 100)/avaliacao.getQuestionario().getRenda();
		msg.append(df.format(porcentagem));
		msg.append("% da sua renda;\n");
		msg.append("Você também nos informou que gasta R$");
		msg.append(avaliacao.getQuestionario().getEducacao());
		msg.append(" com educação, R$");
		msg.append(avaliacao.getQuestionario().getLazer());
		msg.append(" com lazer, R$");
		msg.append(avaliacao.getQuestionario().getTransporte());
		msg.append(" com transporte, R$");
		msg.append(avaliacao.getQuestionario().getOutros());
		msg.append(" com qualuqer outra coisa fora das categorias citadas;\n");
		msg.append("Educação representa ");
		porcentagem = (avaliacao.getQuestionario().getEducacao() * 100)/avaliacao.getQuestionario().getRenda();
		msg.append(df.format(porcentagem));
		msg.append("% da sua renda;\n");
		msg.append("Lazer representa ");
		porcentagem = (avaliacao.getQuestionario().getLazer() * 100)/avaliacao.getQuestionario().getRenda();
		msg.append(df.format(porcentagem));
		msg.append("% da sua renda;\n");
		msg.append("Transporte representa ");
		porcentagem = (avaliacao.getQuestionario().getTransporte() * 100)/avaliacao.getQuestionario().getRenda();
		msg.append(df.format(porcentagem));
		msg.append("% da sua renda;\n");
		msg.append("E outros gastos representam ");
		porcentagem = (avaliacao.getQuestionario().getOutros() * 100)/avaliacao.getQuestionario().getRenda();
		msg.append(df.format(porcentagem));
		msg.append("% da sua renda;\n");
		msg.append("Calculamos que sua renda fica ");
		porcentagem = ((essencial + avaliacao.getQuestionario().getOutros() + avaliacao.getQuestionario().getTransporte()+
				avaliacao.getQuestionario().getLazer() + avaliacao.getQuestionario().getEducacao())*100)/avaliacao.getQuestionario().getRenda();
		msg.append(porcentagem);
		msg.append("% comprometida com gastos mensais fixos. Sobrando R$");
		msg.append(avaliacao.getQuestionario().getRenda() - (essencial + avaliacao.getQuestionario().getOutros() + 
				avaliacao.getQuestionario().getTransporte() + avaliacao.getQuestionario().getLazer() + avaliacao.getQuestionario().getEducacao()));
		msg.append(" para utilizar livremente.\n\n");
		msg.append("Fizemos uma avaliação dos ultimos 6 meses, sobre quanto e como tem gastado seu dinheiro.\n");
		msg.append("Vimos que em média gasta: ");
		for(Saida s:saidas){
			msg.append("R$");
			msg.append(s.getValor());
			msg.append(" com ");
			msg.append(s.getCategoria().getDescricao());
			msg.append(", ");
		}
		msg.append("por mês\n");
		msg.append("No total, você gasta em média R$");
		for(Saida s:saidas){
			aux+= s.getValor();
		}
		msg.append(aux);
		msg.append(".\n");
		if(aux>avaliacao.getQuestionario().getRenda()){
			msg.append("Este valor esta acima da Renda que nos informou.\n");
			msg.append("Sendo assim, acreditados que seja ideal você reduzir custos e talvez agora ");
			msg.append("não seja o momento ideal para projetar Metas.\n\n");
		}
		msg.append("Aqui vão algumas dicas que sempre são bem vindas para reduzir gastos:\n");
		msg.append("	1- Reduza o número de vezes em que come fora.\n");
		msg.append("	2- Evite usar o cartão de crédito.\n");
		msg.append("	3- Registre todos seus gastos para que possamos te ajudar.\n");
		msg.append("	4- 'N' dicas.\n\n");
		aux =0;
		for(Saida s:saidas){
			if(s.getValor()>aux){
				aux = s.getValor();
			}
		}
		for(Saida s:saidas){
			if(aux==s.getValor()){
				msg.append("Identificamos que tem gastado muito com ");
				msg.append(s.getCategoria().getDescricao());
				msg.append(", em média R$");
				msg.append(s.getValor());
				msg.append("Fique atento e tente reduzir nos prósimo meses.\n");
			}
		}
		System.out.println(msg);
		return null;
	}

}
