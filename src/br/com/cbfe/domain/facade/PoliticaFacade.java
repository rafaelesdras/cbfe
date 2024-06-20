package br.com.cbfe.domain.facade;

import java.time.LocalDate;

import br.com.cbfe.domain.dao.BeneficioDAO;
import br.com.cbfe.domain.dao.PoliticaDAO;
import br.com.cbfe.domain.model.Beneficio;
import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Politica;
import br.com.cbfe.domain.strategy.CoparticipacaoStrategy;

public class PoliticaFacade {
	
	public Politica criarPolitica(Empresa empresa, Class<? extends CoparticipacaoStrategy<?>> coparticipacaoStrategy,
			LocalDate dataCriacao, LocalDate dataExpiracao) {
		return PoliticaDAO.getInstance().salvar(new Politica.PoliticaBuilder(BeneficioDAO.getInstance()
				.proximoCodigo(empresa), empresa)
				.setCoparticipacaoStrategy(coparticipacaoStrategy)
				.setDataCriacao(dataCriacao)
				.setDataExpiracao(dataExpiracao)
				.build());
	}
	
	public void vincularABeneficio(Politica politica, Beneficio beneficio) {
		beneficio.adicionarPolitica(politica);
		BeneficioDAO.getInstance().salvar(beneficio);
	}
	
	public void desvincularDeBeneficio(Politica politica, Beneficio beneficio) {
		beneficio.removerPolitica(politica);
		BeneficioDAO.getInstance().salvar(beneficio);
	}
	
	public boolean excluir(Politica politica) {
		if (BeneficioDAO.getInstance().buscarPorEmpresa(politica.getEmpresa()).stream()
			.anyMatch(beneficio -> beneficio.getPoliticas().contains(politica))) {
			throw new IllegalArgumentException("Há benefícios vinculados a esta política.");
		}
		
		return PoliticaDAO.getInstance().excluir(politica);
	}

}
