package br.com.controlmore.vm;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.viewmodel.IViewModel;

public class ResumoVM implements IViewModel {

	//Será utilizado para receber o saldo total calculado
	private double saldo;
	
	//Serão utilizados para gerar o gráfico mensal de lançamentos
	private List<Integer> dias = new ArrayList<Integer>();
	private List<Double> cPagas = new ArrayList<Double>();
	private List<Double> cRecebidas = new ArrayList<Double>();
	
	//Serão utilizadas para controle de contas proximas ao vencimento (recebimento e pagamento)
	private List<Saida> aPagar = new ArrayList<Saida>();
	private List<Entrada> aReceber = new ArrayList<Entrada>();
	
	//Serão utilizadas para controle de contas vencidas (recebimento e pagamento)
	private List<Saida> aPagarVencida = new ArrayList<Saida>();
	private List<Entrada> aReceberAtrasada = new ArrayList<Entrada>();
	
	//Será usado para armezenar as contas e saldo
	private List<Conta> saldoContas = new ArrayList<Conta>();
	
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public List<Integer> getDias() {
		return dias;
	}
	public void setDias(int dia) {
		this.dias.add(dia);
	}
	public List<Double> getcPagas() {
		return cPagas;
	}
	public void setcPagas(double cPagas) {
		this.cPagas.add(cPagas);
	}
	public List<Double> getcRecebidas() {
		return cRecebidas;
	}
	public void setcRecebidas(double cRecebidas) {
		this.cRecebidas.add(cRecebidas);
	}
	
	public void setaPagar(Saida s) {
		this.aPagar.add(s);
	}
	public List<Saida> getaPagar(){
		return aPagar;
	}
	public void setaReceber(Entrada e){
		this.aReceber.add(e);
	}
	public List<Entrada> getaReceber(){
		return aReceber;
	}
	
	public void setaPagarVencida(Saida s) {
		this.aPagarVencida.add(s);
	}
	public List<Saida> getaPagarVencida(){
		return aPagarVencida;
	}
	public void setaReceberAtrasada(Entrada e){
		this.aReceberAtrasada.add(e);
	}
	public List<Entrada> getaReceberAtrasada(){
		return aReceberAtrasada;
	}
	
	public void setSaldoContas(Conta c){
		this.saldoContas.add(c);
	}
	public List<Conta> getSaldoContas(){
		return saldoContas;
	}
}
