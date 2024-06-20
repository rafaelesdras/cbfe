package br.com.cbfe.domain.facade;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cbfe.domain.dao.GuiaDAO;
import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.Guia;

public class IntegracaoFacade {
	
	private static final String[] MESES = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
			"Setembro", "Outubro", "Novembro", "Dezembro"};
	
	public void gerarResumoParaFolhaDoMes(Empresa empresa, Month mes, int ano) {
		GuiaDAO.getInstance().buscarPorEmpresa(empresa).stream()
			.filter(guia -> !guia.isPendenteDeAnalise())
			.filter(guia -> guia.getDataAnalise().getMonth().equals(mes))
			.collect(Collectors.groupingBy(Guia::getFuncionario))
			.forEach((funcionario, guias) -> {
				var mensagem = new StringBuilder()
						.append("No mês de ")
						.append(MESES[mes.getValue() - 1])
						.append(" de ")
						.append(ano)
						.append(" o funcionário ")
						.append(funcionario.getNome())
						.append(" terá ")
						.append(somarGuias(guias))
						.append(" de desconto em folha relativo a concessão de benefícios.");
				
				System.out.println(mensagem);
			});
	}
	
	private Double somarGuias(List<Guia> guias) {
		return guias.stream()
				.flatMap(guia -> guia.getItens().stream())
				.map(item -> item.getQuantidadeLiberada() * item.getValorUnitario())
				.reduce(Double.valueOf(0D), (a, b) -> a + b);
	}

}
