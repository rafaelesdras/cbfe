package br.com.cbfe.domain.facade;

import br.com.cbfe.domain.dao.BeneficioDAO;
import br.com.cbfe.domain.model.Beneficio;
import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Politica;

public class BeneficioFacade {
	
	public Beneficio criarBeneficio(String nome, Empresa empresa, Double valorUnitario) {
		return BeneficioDAO.getInstance().salvar(new Beneficio.BeneficioBuilder(BeneficioDAO.getInstance()
				.proximoCodigo(empresa), nome, empresa, valorUnitario).build());
	}
	
	public void vincularPolitica(Beneficio beneficio, Politica politica) {
		beneficio.adicionarPolitica(politica);
		BeneficioDAO.getInstance().salvar(beneficio);
	}
	
	public void desvincularPolitica(Beneficio beneficio, Politica politica) {
		beneficio.removerPolitica(politica);
		BeneficioDAO.getInstance().salvar(beneficio);
	}
	
	public boolean excluir(Beneficio beneficio) {
		return BeneficioDAO.getInstance().excluir(beneficio);
	}

}
