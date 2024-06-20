package br.com.cbfe.domain.strategy;

import br.com.cbfe.domain.model.Funcionario;

public interface CoparticipacaoStrategy<T> {
	
	Double getPercentualCoparticipacao();
	
	boolean isAplicavel(Funcionario funcionario);

}
