package br.com.controlmore.negocio;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Meta;
import sun.management.resources.agent;

public class SimularMeta implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Meta meta = (Meta) entidade;
		if(meta.getPrazo()== null){
			/*o Prazo deverá ser calculado levando em consideração o valor total dividido pelo valor mensal
			1º dividir valor total pelo valor mensal
				- O resultado será a quantidade de meses que serão necessários acresentar na data atual
			2º pegar a data atual e acrescentar o resultado da divisão para gerar o prazo*/
			int meses = (int) (meta.getValorTotal()/meta.getValorMensal());
			LocalDate prazo = LocalDate.now().plusMonths(meses);
			LocalDate now = LocalDate.now();
			//Converte Local Date para Date
            Instant instant = prazo.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            
            // Obtains a period consisting of the number of years, months and days
	        // between two dates.
	        Period age = Period.between(now, prazo);
            
            meta.setPrazo(Date.from(instant));
			StringBuilder msg = new StringBuilder();
			msg.append("Se você guardar R$");
			msg.append(meta.getValorMensal());
			msg.append(" por mês até ");
			msg.append(prazo.getDayOfMonth());
			msg.append("/");
			msg.append(prazo.getMonthValue());
			msg.append("/");
			msg.append(prazo.getYear());
			msg.append(" você consiguirá atingir sua meta de R$");
			msg.append(meta.getValorTotal());
			msg.append("\n");
			msg.append("Falta");
			if(age.getYears()==1){
				msg.append(" ");
				msg.append(age.getYears());
				msg.append(" ano e ");
			}
			if(age.getYears()>1){
				msg.append("m ");
				msg.append(age.getYears());
				msg.append(" anos e ");
			}
			if(age.getMonths()==1){
				if(age.getYears()==0)
					msg.append("m ");
				msg.append(age.getMonths());
				msg.append(" mês");
			}
			if(age.getMonths()>1 || age.getMonths()==0){
				if(age.getYears()==0)
					msg.append("m ");
				msg.append(age.getMonths());
				msg.append(" meses");
			}
			msg.append(" pra você atingir seu objetivo.");
			meta.setMsg(msg.toString());
			//return msg.toString();
			
		}
		if(meta.getValorMensal()==0){
			/* Deverá calcular o valor que deve ser poupado mensalmente para atingir o valor total no tempo esperado.
			1º calcular a diferença entre a data atual e o prazo final. 
			2º dividir o valor total pela diferença entre as datas */

	        LocalDate now = LocalDate.now();
	        //Converte Date para LocalDate
			Instant instant = Instant.ofEpochMilli(meta.getPrazo().getTime());
	        LocalDate prazo = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

	        // Obtains a period consisting of the number of years, months and days
	        // between two dates.
	        Period age = Period.between(now, prazo);

	        // Using ChronoUnit to calculate difference in years, months and days
	        // between two dates.
	        //long years = ChronoUnit.YEARS.between(now, prazo);
	        long months = ChronoUnit.MONTHS.between(now, prazo);
	        //long days = ChronoUnit.DAYS.between(now, prazo);
	        
	        //Converte Local Date para Date
	        /*LocalDate localDate = LocalDate.now();
            Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date data = Date.from(instant);*/
            float mensal = 0;
            mensal = meta.getValorTotal()/months;
            meta.setValorMensal(mensal);
	       
            StringBuilder msg = new StringBuilder();
			msg.append("Se você guardar R$");
			msg.append(mensal);
			msg.append(" por mês até ");
			msg.append(prazo.getDayOfMonth());
			msg.append("/");
			msg.append(prazo.getMonthValue());
			msg.append("/");
			msg.append(prazo.getYear());
			msg.append(" você consiguirá atingir sua meta de R$");
			msg.append(meta.getValorTotal());
			msg.append("\n");
			msg.append("Falta");
			if(age.getYears()==1){
				msg.append(" ");
				msg.append(age.getYears());
				msg.append(" ano e ");
			}
			if(age.getYears()>1){
				msg.append("m ");
				msg.append(age.getYears());
				msg.append(" anos e ");
			}
			if(age.getMonths()==1){
				if(age.getYears()==0)
					msg.append(" ");
				msg.append(age.getMonths());
				msg.append(" mês");
			}
			if(age.getMonths()>1 || age.getMonths()==0){
				if(age.getYears()==0)
					msg.append("m ");
				msg.append(age.getMonths());
				msg.append(" meses");
			}
			msg.append(" pra você atingir seu objetivo.");
			meta.setMsg(msg.toString());
			//return msg.toString();
		}
		
		return null;
	}

	@Override
	public String processar(Date dtInicio, Date dtFinal) {
		// TODO Auto-generated method stub
		return null;
	}

}
