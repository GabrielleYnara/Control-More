package br.com.controlmore.testes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.time.temporal.ChronoUnit;

import br.com.controlmore.dominio.Meta;
import br.com.controlmore.negocio.SimularMeta;
import oracle.net.aso.d;

public class TesteSimularMeta {
	public static void main(String[] args) {

		//salvar();
		//alterar();
		//excluir();
		//consultar();
		simular();
		//DateDifference();
		
	}
	public static void simular(){
		Meta meta = new Meta();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = formatter.parse("20/10/2017");
			//meta.setPrazo(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meta.setValorTotal(5850f);
		meta.setValorMensal(200f);
		SimularMeta sm = new SimularMeta();
		sm.processar(meta);
		
	}
	public static void DateDifference() {
		
	       	
	}
}
