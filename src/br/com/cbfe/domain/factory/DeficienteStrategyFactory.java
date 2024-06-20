package br.com.cbfe.domain.factory;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.strategy.DeficienteCoparticipacaoStrategy;

public class DeficienteStrategyFactory extends Factory<DeficienteCoparticipacaoStrategy> {

	@Override
	public DeficienteCoparticipacaoStrategy create(Builder<DeficienteCoparticipacaoStrategy> builder) {
		return builder.build();
	}

}
