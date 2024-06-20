package br.com.cbfe.domain.dao;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Funcionario;
import br.com.cbfe.domain.model.Guia;

public class GuiaDAO {
	
	private static GuiaDAO instance;
	
	private static final Set<Guia> guias = new HashSet<>(0);
	
	private GuiaDAO() {
		
	}
	
	public static GuiaDAO getInstance() {
		if (instance == null) {
			instance = new GuiaDAO();
		}
		
		return instance;
	}
	
	public Long proximoNumeroGuia() {
		return new Random().nextLong();
	}
	
	public Long proximoNumeroItem(Guia guia) {
		return guia.getItens().stream().count() + 1;
	}
	
	public Guia buscarPorNumero(Long numero) {
		return guias.stream().filter(guia -> guia.getNumero().equals(numero)).findFirst().orElse(null);
	}
	
	public Set<Guia> buscarPorFuncionario(Funcionario funcionario) {
		return guias.stream()
				.filter(guia -> guia.getFuncionario().equals(funcionario))
				.collect(Collectors.toSet());
	}
	
	public Set<Guia> buscarPorEmpresa(Empresa empresa) {
		return guias.stream()
				.filter(guia -> guia.getFuncionario().getCargo().getEmpresa().equals(empresa))
				.collect(Collectors.toSet());
	}
	
	public Guia salvar(Guia guia) {
		guias.add(guia);
		
		return guia;
	}
	
	public boolean excluir(Guia guia) {
		return guias.remove(guia);
	}

}
