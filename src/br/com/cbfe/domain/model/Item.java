package br.com.cbfe.domain.model;

import java.util.List;

public class Item {
	
	private Long numero;
	
	private Guia guia;
	
	private Beneficio beneficio;
	
	private Long quantidadeSolicitada;
	
	private Double valorUnitario;
	
	private ResultadoAnalise resultadoAnalise;
	
	private Long quantidadeLiberada;
	
	public Item(Long numero, Guia guia, Beneficio beneficio, Long quantidadeSolicitada) {
		setNumero(numero);
		setGuia(guia);
		setBeneficio(beneficio);
		setQuantidadeSolicitada(quantidadeSolicitada);
		setValorUnitario(beneficio.getValorUnitario());
		setResultadoAnalise(ResultadoAnalise.AGUARDANDO_ANALISE);
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	public Long getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Long quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public ResultadoAnalise getResultadoAnalise() {
		return resultadoAnalise;
	}

	public void setResultadoAnalise(ResultadoAnalise resultadoAnalise) {
		this.resultadoAnalise = resultadoAnalise;
	}

	public Long getQuantidadeLiberada() {
		return quantidadeLiberada;
	}

	public void setQuantidadeLiberada(Long quantidadeLiberada) {
		this.quantidadeLiberada = quantidadeLiberada;
	}
	
	public boolean isPendenteDeAnalise() {
		return List.of(ResultadoAnalise.AGUARDANDO_ANALISE, ResultadoAnalise.EM_ANALISE).contains(resultadoAnalise);
	}

}
