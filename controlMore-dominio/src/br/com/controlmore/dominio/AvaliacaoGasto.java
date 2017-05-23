package br.com.controlmore.dominio;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoGasto extends EntidadeDominio{
	private List<Saida> saidas = new ArrayList<Saida>();
	private Questionario questionario;
	private String msg;
	
	public List<Saida> getSaidas() {
		return saidas;
	}
	public void setSaidas(List<Saida> saidas) {
		this.saidas = saidas;
	}
	public Questionario getQuestionario() {
		return questionario;
	}
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
