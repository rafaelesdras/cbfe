package br.com.cbfe.domain.facade;

import java.time.LocalDate;

import br.com.cbfe.domain.dao.FuncionarioDAO;
import br.com.cbfe.domain.model.Cargo;
import br.com.cbfe.domain.model.FormaAcompanhamento;
import br.com.cbfe.domain.model.Funcionario;

public class FuncionarioFacade {
	
	public Funcionario contratarFuncionario(String nome, Cargo cargo, LocalDate dataAdmissao,
			boolean possuiDeficiencia, FormaAcompanhamento formaAcompanhamento) {
		return FuncionarioDAO.getInstance().salvar(new Funcionario(FuncionarioDAO.getInstance()
				.proximaMatricula(cargo.getEmpresa()), nome, cargo, dataAdmissao, possuiDeficiencia,
				formaAcompanhamento));
	}
	
	public void demitirFuncionario(Funcionario funcionario, LocalDate dataDemissao) {
		funcionario.setDataDemissao(dataDemissao);
		FuncionarioDAO.getInstance().salvar(funcionario);
	}
	
	public void alterarFormaDeAcompanhemento(Funcionario funcionario, FormaAcompanhamento formaAcompanhamento) {
		funcionario.setFormaAcompanhamento(formaAcompanhamento);
		FuncionarioDAO.getInstance().salvar(funcionario);
	}

}
