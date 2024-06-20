package br.com.cbfe.domain.factory;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.strategy.CargoCoparticipacaoStrategy;

public class CargoStrategyFactory extends Factory<CargoCoparticipacaoStrategy> {

	@Override
	public CargoCoparticipacaoStrategy create(Builder<CargoCoparticipacaoStrategy> builder) {
		return builder.build();
	}

}
