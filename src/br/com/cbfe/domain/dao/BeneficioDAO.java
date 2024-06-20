package br.com.cbfe.domain.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.cbfe.domain.model.Beneficio;
import br.com.cbfe.domain.model.Empresa;

public class BeneficioDAO {
	
	private static BeneficioDAO instance;
	
	private static final Set<Beneficio> beneficios = new HashSet<>(0);
	
	private BeneficioDAO() {
		
	}
	
	public static BeneficioDAO getInstance() {
		if (instance == null) {
			instance = new BeneficioDAO();
		}
		
		return instance;
	}
	
	public Long proximoCodigo(Empresa empresa) {
		return beneficios.stream()
			.filter(beneficio -> beneficio.getEmpresa().equals(empresa)).count() + 1;
	}
	
	public Beneficio buscarPorCodigo(Long codigo) {
		return beneficios.stream().filter(beneficio -> beneficio.getCodigo().equals(codigo))
				.findFirst().orElse(null);
	}
	
	public Set<Beneficio> buscarPorEmpresa(Empresa empresa) {
		return beneficios.stream()
				.filter(policita -> policita.getEmpresa().equals(empresa))
				.collect(Collectors.toSet());
	}
	
	public Beneficio salvar(Beneficio beneficio) {
		beneficios.add(beneficio);
		
		return beneficio;
	}
	
	public boolean excluir(Beneficio beneficio) {
		return beneficios.remove(beneficio);
	}

}
