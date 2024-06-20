package br.com.cbfe.domain.facade;

import java.util.Comparator;
import java.util.stream.Collectors;

import br.com.cbfe.domain.dao.GuiaDAO;
import br.com.cbfe.domain.dao.PoliticaDAO;
import br.com.cbfe.domain.factory.CargoStrategyFactory;
import br.com.cbfe.domain.factory.DeficienteStrategyFactory;
import br.com.cbfe.domain.factory.SenioridadeStrategyFactory;
import br.com.cbfe.domain.model.Beneficio;
import br.com.cbfe.domain.model.Funcionario;
import br.com.cbfe.domain.model.Guia;
import br.com.cbfe.domain.model.Item;
import br.com.cbfe.domain.model.Politica;
import br.com.cbfe.domain.model.ResultadoAnalise;
import br.com.cbfe.domain.strategy.CargoCoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.CargoCoparticipacaoStrategy.CargoCoparticipacaoStrategyBuilder;
import br.com.cbfe.domain.strategy.CoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.DeficienteCoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.DeficienteCoparticipacaoStrategy.DeficienteCoparticipacaoStrategyBuilder;
import br.com.cbfe.domain.strategy.SenioridadeCoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.SenioridadeCoparticipacaoStrategy.SenioridadeCoparticipacaoStrategyBuilder;

public class GuiaFacade {
	
	public Guia gerarGuia(Funcionario funcionario) {
		return GuiaDAO.getInstance().salvar(new Guia.GuiaBuilder(GuiaDAO.getInstance().proximoNumeroGuia(),
				funcionario).build());
	}
	
	public Item adicionarItem(Guia guia, Beneficio beneficio, Long quantidadeSolicitada) {
		var item = new Item(GuiaDAO.getInstance().proximoNumeroItem(guia), guia, beneficio, quantidadeSolicitada);
		
		guia.adicionarItem(item);
		GuiaDAO.getInstance().salvar(guia);
		
		return item;
	}
	
	public boolean removerItem(Guia guia, Item item) {
		return guia.removerItem(item);
	}
	
	public void aprovarGuia(Guia guia) {
		guia.aprovar();
		calcularValores(guia);
		GuiaDAO.getInstance().salvar(guia);
	}
	
	public void indeferirGuia(Guia guia) {
		guia.indeferir();
		calcularValores(guia);
		GuiaDAO.getInstance().salvar(guia);
	}
	
	public void concluirAnaliseGuia(Guia guia) {
		if (guia.isPendenteDeAnalise()) {
			throw new IllegalArgumentException("Guia pendente de análise.");
		}
		
		guia.setResultadoAnalise(extrairResultadoAnalise(guia));
		calcularValores(guia);
		GuiaDAO.getInstance().salvar(guia);
	}
	
	private ResultadoAnalise extrairResultadoAnalise(Guia guia) {
		if (guia.getItens().stream().allMatch(item -> ResultadoAnalise.APROVADO.equals(item.getResultadoAnalise()))) {
			return ResultadoAnalise.APROVADO;
		}
		
		if (guia.getItens().stream().allMatch(item -> ResultadoAnalise.INDEFERIDO.equals(item.getResultadoAnalise()))) {
			return ResultadoAnalise.INDEFERIDO;
		}
		
		return ResultadoAnalise.PARCIALMENTE_APROVADO;
	}
	
	public void analisarItem(Item item, Long quantidadeLiberada) {
		if (quantidadeLiberada == null ||
				quantidadeLiberada > item.getQuantidadeSolicitada()) {
			throw new IllegalArgumentException("Quantidade liberada inválida.");
		}
		
		if (item.getQuantidadeSolicitada().equals(quantidadeLiberada)) {
			item.setResultadoAnalise(ResultadoAnalise.APROVADO);
		} else if (quantidadeLiberada.equals(0L)){
			item.setResultadoAnalise(ResultadoAnalise.INDEFERIDO);
		} else {
			item.setResultadoAnalise(ResultadoAnalise.PARCIALMENTE_APROVADO);
		}
		
		item.setQuantidadeLiberada(quantidadeLiberada);
		item.getGuia().setResultadoAnalise(ResultadoAnalise.EM_ANALISE);
		GuiaDAO.getInstance().salvar(item.getGuia());
	}
	
	public boolean excluir(Guia guia) {
		return GuiaDAO.getInstance().excluir(guia);
	}
	
	public void concluirSolicitacao(Guia guia) {
		guia.concluirSolicitacao();
		GuiaDAO.getInstance().salvar(guia);
	}
	
	public void calcularValores(Guia guia) {
		var funcionario = guia.getFuncionario();
		var coparticipacaoStrategies = PoliticaDAO.getInstance().buscarPorEmpresa(funcionario.getCargo().getEmpresa())
				.stream()
				.map(Politica::getCoparticipacaoStrategy)
				.collect(Collectors.toList());
		
		var percentualCoparticipacao = coparticipacaoStrategies.stream()
				.map(coparticipacaoStrategy -> criarFactory(coparticipacaoStrategy, funcionario))
				.filter(coparticipacaoStrategy -> coparticipacaoStrategy.isAplicavel(funcionario))
				.map(CoparticipacaoStrategy::getPercentualCoparticipacao)
			.min(Comparator.comparing(i -> i))
			.orElse(Double.valueOf(100D));
		
		guia.getItens().forEach(item -> item.setValorUnitario((item.getValorUnitario() * percentualCoparticipacao) / 100));
		GuiaDAO.getInstance().salvar(guia);
	}

	private CoparticipacaoStrategy<?> criarFactory(Class<? extends CoparticipacaoStrategy<?>> clazz,
			Funcionario funcionario) {
		if (clazz.isAssignableFrom(DeficienteCoparticipacaoStrategy.class)) {
			return new DeficienteStrategyFactory().create(new DeficienteCoparticipacaoStrategyBuilder());
		}
		
		if (clazz.isAssignableFrom(CargoCoparticipacaoStrategy.class)) {
			return new CargoStrategyFactory().create(new CargoCoparticipacaoStrategyBuilder(funcionario.getCargo()));
		}
		
		if (clazz.isAssignableFrom(SenioridadeCoparticipacaoStrategy.class)) {
			return new SenioridadeStrategyFactory().create(new SenioridadeCoparticipacaoStrategyBuilder(
					funcionario.getTempoContratacaoEmAnos(), funcionario.getSenioridade()));
		}
		
		throw new IllegalArgumentException("Não foi possível realizar o cálculo de coparticipação.");
	}

}
