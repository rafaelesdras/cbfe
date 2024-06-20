package br.com.cbfe.domain.strategy;

import br.com.cbfe.domain.Factory;
import br.com.cbfe.domain.model.Cargo;
import br.com.cbfe.domain.model.Funcionario;

public class CargoCoparticipacaoStrategy implements CoparticipacaoStrategy<CargoCoparticipacaoStrategy> {
	
	private Cargo cargo;
	
	private CargoCoparticipacaoStrategy(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public Double getPercentualCoparticipacao() {
		return cargo.getPercentualCoparticipacao();
	}

	@Override
	public boolean isAplicavel(Funcionario funcionario) {
		return cargo.equals(funcionario.getCargo());
	}

	public Cargo getCargo() {
		return cargo;
	}
	
	public static class CargoCoparticipacaoStrategyBuilder extends Factory.Builder<CargoCoparticipacaoStrategy> {

		private Cargo cargo;
		
		public CargoCoparticipacaoStrategyBuilder(Cargo cargo) {
			this.cargo = cargo;
		}
		
		public CargoCoparticipacaoStrategy build() {
			return new CargoCoparticipacaoStrategy(cargo);
		}
	}

}
