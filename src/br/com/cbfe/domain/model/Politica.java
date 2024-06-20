package br.com.cbfe.domain.model;

import java.time.LocalDate;

import br.com.cbfe.domain.strategy.CoparticipacaoStrategy;

public class Politica {
	
	private Long codigo;
	
	private Empresa empresa;
	
	private Class<? extends CoparticipacaoStrategy<?>> coparticipacaoStrategy;
	
	private LocalDate dataCriacao;
	
	private LocalDate dataExpiracao;
	
	private boolean restritaAFuncionariosComDeficiencia;
	
	private Politica(PoliticaBuilder builder) {
		this.codigo = builder.codigo;
		this.empresa = builder.empresa;
		this.coparticipacaoStrategy = builder.coparticipacaoStrategy;
		this.dataCriacao = builder.dataCriacao;
		this.dataExpiracao = builder.dataExpiracao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public Class<? extends CoparticipacaoStrategy<?>> getCoparticipacaoStrategy() {
		return coparticipacaoStrategy;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public boolean isRestritaAFuncionariosComDeficiencia() {
		return restritaAFuncionariosComDeficiencia;
	}
	
	public static class PoliticaBuilder {

		private Long codigo;
		
		private Empresa empresa;
		
		private Class<? extends CoparticipacaoStrategy<?>> coparticipacaoStrategy;
		
		private LocalDate dataCriacao;
		
		private LocalDate dataExpiracao;
		
		public PoliticaBuilder(Long codigo, Empresa empresa) {
			this.codigo = codigo;
			this.empresa = empresa;
		}

		public PoliticaBuilder setCoparticipacaoStrategy(Class<? extends CoparticipacaoStrategy<?>> coparticipacaoStrategy) {
			this.coparticipacaoStrategy = coparticipacaoStrategy;
			return this;
		}

		public PoliticaBuilder setDataCriacao(LocalDate dataCriacao) {
			this.dataCriacao = dataCriacao;
			return this;
		}

		public PoliticaBuilder setDataExpiracao(LocalDate dataExpiracao) {
			this.dataExpiracao = dataExpiracao;
			return this;
		}
		
		public Politica build(){
			return new Politica(this);
		}

	}

}
