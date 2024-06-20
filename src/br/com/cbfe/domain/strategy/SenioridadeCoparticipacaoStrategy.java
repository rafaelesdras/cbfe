package br.com.cbfe.domain.strategy;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.model.Funcionario;
import br.com.cbfe.domain.model.Senioridade;

public class SenioridadeCoparticipacaoStrategy implements CoparticipacaoStrategy<SenioridadeCoparticipacaoStrategy> {
	
	private int tempoContratacaoEmAnos;
	
	private Senioridade senioridade;
	
	private SenioridadeCoparticipacaoStrategy(int tempoContratacaoEmAnos, Senioridade senioridade) {
		this.tempoContratacaoEmAnos = tempoContratacaoEmAnos;
		this.senioridade = senioridade;
	}

	@Override
	public Double getPercentualCoparticipacao() {
		return 100 - (getTempoContratacaoEmAnos() * getSenioridade().getPercentualDescontoPorAno());
	}

	@Override
	public boolean isAplicavel(Funcionario funcionario) {
		return senioridade.equals(funcionario.getSenioridade());
	}

	public int getTempoContratacaoEmAnos() {
		return tempoContratacaoEmAnos;
	}

	public void setTempoContratacaoEmAnos(int tempoContratacaoEmAnos) {
		this.tempoContratacaoEmAnos = tempoContratacaoEmAnos;
	}

	public Senioridade getSenioridade() {
		return senioridade;
	}

	public void setSenioridade(Senioridade senioridade) {
		this.senioridade = senioridade;
	}
	
	public static class SenioridadeCoparticipacaoStrategyBuilder extends
		Factory.Builder<SenioridadeCoparticipacaoStrategy> {

		private int tempoContratacaoEmAnos;
		
		private Senioridade senioridade;
		
		public SenioridadeCoparticipacaoStrategyBuilder(int tempoContratacaoEmAnos, Senioridade senioridade) {
			this.tempoContratacaoEmAnos = tempoContratacaoEmAnos;
			this.senioridade = senioridade;
		}

		public SenioridadeCoparticipacaoStrategyBuilder setTempoContratacaoEmAnos(int tempoContratacaoEmAnos) {
			this.tempoContratacaoEmAnos = tempoContratacaoEmAnos;
			return this;
		}

		public SenioridadeCoparticipacaoStrategyBuilder setSenioridade(Senioridade senioridade) {
			this.senioridade = senioridade;
			return this;
		}
		
		public SenioridadeCoparticipacaoStrategy build() {
			return new SenioridadeCoparticipacaoStrategy(tempoContratacaoEmAnos, senioridade);
		}
	}

}
