package br.com.cbfe.domain.factory;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.strategy.SenioridadeCoparticipacaoStrategy;

public class SenioridadeStrategyFactory extends Factory<SenioridadeCoparticipacaoStrategy> {

	@Override
	public SenioridadeCoparticipacaoStrategy create(Builder<SenioridadeCoparticipacaoStrategy> builder) {
		return builder.build();
	}

}
