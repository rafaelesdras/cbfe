package br.com.cbfe.domain.dao;

import java.util.HashSet;
import java.util.Set;

import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Funcionario;

public class FuncionarioDAO {
	
	private static FuncionarioDAO instance;
	
	private static final Set<Funcionario> funcionarios = new HashSet<>(0);
	
	private FuncionarioDAO() {
		
	}
	
	public static FuncionarioDAO getInstance() {
		if (instance == null) {
			instance = new FuncionarioDAO();
		}
		
		return instance;
	}
	
	public Long proximaMatricula(Empresa empresa) {
		return funcionarios.stream()
			.filter(funcionario -> funcionario.getCargo().getEmpresa().equals(empresa)).count() + 1;
	}
	
	public Funcionario buscarPorMatricula(Long matricula) {
		return funcionarios.stream().filter(funcionario -> funcionario.getMatricula().equals(matricula))
				.findFirst().orElse(null);
	}
	
	public Set<Funcionario> buscarTodos() {
		return funcionarios;
	}
	
	public Funcionario salvar(Funcionario funcionario) {
		funcionarios.add(funcionario);
		
		return funcionario;
	}
}
