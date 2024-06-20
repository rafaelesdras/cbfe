package br.com.cbfe.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Beneficio {
	
	private Long codigo;
	
	private String nome;
	
	private Empresa empresa;
	
	private Double valorUnitario;
	
	private List<Politica> politicas;
	
	private Beneficio(BeneficioBuilder builder) {
		this.codigo = builder.codigo;
		this.nome = builder.nome;
		this.empresa = builder.empresa;
		this.valorUnitario = builder.valorUnitario;
		this.politicas = builder.politicas == null ? new ArrayList<>(0) : builder.politicas;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public List<Politica> getPoliticas() {
		return politicas;
	}

	public void adicionarPolitica(Politica politica) {
		this.politicas.add(politica);
	}

	public void removerPolitica(Politica politica) {
		this.politicas.remove(politica);
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public static class BeneficioBuilder {

		private Long codigo;
		
		private String nome;
		
		private Empresa empresa;
		
		private Double valorUnitario;
		
		private List<Politica> politicas;
		
		public BeneficioBuilder(Long codigo, String nome, Empresa empresa, Double valorUnitario) {
			this.codigo = codigo;
			this.nome = nome;
			this.empresa = empresa;
			this.valorUnitario = valorUnitario;
		}

		public BeneficioBuilder setPoliticas(List<Politica> politicas) {
			this.politicas = politicas;
			return this;
		}
		
		public Beneficio build(){
			return new Beneficio(this);
		}

	}

}
