package br.com.cbfe.domain.strategy;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.model.Funcionario;

public class DeficienteCoparticipacaoStrategy implements CoparticipacaoStrategy<DeficienteCoparticipacaoStrategy> {

	private DeficienteCoparticipacaoStrategy() {
		
	}
	
	@Override
	public Double getPercentualCoparticipacao() {
		return Double.valueOf(0D);
	}

	@Override
	public boolean isAplicavel(Funcionario funcionario) {
		return funcionario.isPossuiDeficiencia();
	}
	
	public static class DeficienteCoparticipacaoStrategyBuilder
		extends Factory.Builder<DeficienteCoparticipacaoStrategy> {
		
		public DeficienteCoparticipacaoStrategyBuilder() {

		}

		@Override
		public DeficienteCoparticipacaoStrategy build() {
			return new DeficienteCoparticipacaoStrategy();
		}
	}

}
