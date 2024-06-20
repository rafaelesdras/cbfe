package br.com.cbfe.domain.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Politica;

public class PoliticaDAO {
	
	private static PoliticaDAO instance;
	
	private static final Set<Politica> politicas = new HashSet<>(0);
	
	private PoliticaDAO() {
		
	}
	
	public static PoliticaDAO getInstance() {
		if (instance == null) {
			instance = new PoliticaDAO();
		}
		
		return instance;
	}
	
	public Long proximoCodigo(Empresa empresa) {
		return politicas.stream()
			.filter(politica -> politica.getEmpresa().equals(empresa)).count() + 1;
	}
	
	public Politica buscarPorCodigo(Long codigo) {
		return politicas.stream().filter(politica -> politica.getCodigo().equals(codigo))
				.findFirst().orElse(null);
	}
	
	public Set<Politica> buscarPorEmpresa(Empresa empresa) {
		return politicas.stream()
				.filter(policita -> policita.getEmpresa().equals(empresa))
				.collect(Collectors.toSet());
	}
	
	public Politica salvar(Politica politica) {
		politicas.add(politica);
		
		return politica;
	}
	
	public boolean excluir(Politica politica) {
		return politicas.remove(politica);
	}

}
