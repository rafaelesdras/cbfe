package br.com.cbfe;

import java.time.LocalDate;

import br.com.cbfe.domain.facade.BeneficioFacade;
import br.com.cbfe.domain.facade.FuncionarioFacade;
import br.com.cbfe.domain.facade.GuiaFacade;
import br.com.cbfe.domain.facade.IntegracaoFacade;
import br.com.cbfe.domain.facade.PoliticaFacade;
import br.com.cbfe.domain.model.Cargo;
import br.com.cbfe.domain.model.Empresa;
import br.com.cbfe.domain.model.FormaAcompanhamento;
import br.com.cbfe.domain.strategy.CargoCoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.DeficienteCoparticipacaoStrategy;
import br.com.cbfe.domain.strategy.SenioridadeCoparticipacaoStrategy;

public class CBFEApplication {
	
	private static FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
	
	private static PoliticaFacade politicaFacade = new PoliticaFacade();
	
	private static BeneficioFacade beneficioFacade = new BeneficioFacade();
	
	private static GuiaFacade guiaFacade = new GuiaFacade();
	
	private static IntegracaoFacade integracaoFacade = new IntegracaoFacade();
	
	public static void main(String[] args) {
		// Criando empresa
        var empresa = new Empresa(1L, "Empresa X");
        
        cenario(empresa);
    }
	
	private static void cenario(Empresa empresa) {
		// Criação de cargos
        var estagiario = new Cargo(1L, "Estagiário", empresa, Double.valueOf(100D));
        var programador = new Cargo(2L, "Programador", empresa, Double.valueOf(30D));
        var diretor = new Cargo(3L, "Diretor", empresa, Double.valueOf(10D));
        
        // Cadastro de funcionários
        var jose = funcionarioFacade.contratarFuncionario("José", estagiario, LocalDate.now(), false,
        		FormaAcompanhamento.NAO_ACOMPANHAR);
        var pedro = funcionarioFacade.contratarFuncionario("Pedro", programador, LocalDate.now(), true,
        		FormaAcompanhamento.E_MAIL);
        var maria = funcionarioFacade.contratarFuncionario("Maria", diretor, LocalDate.now(), false,
        		FormaAcompanhamento.SMS);
        
        // Cadastro de políticas de cálculos
        var deficiente = politicaFacade.criarPolitica(empresa, DeficienteCoparticipacaoStrategy.class, null, null);
        var senioridade = politicaFacade.criarPolitica(empresa, SenioridadeCoparticipacaoStrategy.class, null, null);
        var diretoria = politicaFacade.criarPolitica(empresa, CargoCoparticipacaoStrategy.class, null, null);
        
        // Cadastro de benefícios
        var fisioterapia = beneficioFacade.criarBeneficio("Fisioterapia", empresa, Double.valueOf(100D));
        
        // Vinculando políticas aos benefícios
        beneficioFacade.vincularPolitica(fisioterapia, deficiente);
        beneficioFacade.vincularPolitica(fisioterapia, senioridade);
        beneficioFacade.vincularPolitica(fisioterapia, diretoria);
        
        // Realização de pedidos
        var guiaJose = guiaFacade.gerarGuia(jose);
        guiaFacade.adicionarItem(guiaJose, fisioterapia, 1L);
        guiaFacade.concluirSolicitacao(guiaJose);
        
        var guiaPedro = guiaFacade.gerarGuia(pedro);
        guiaFacade.adicionarItem(guiaPedro, fisioterapia, 2L);
        guiaFacade.concluirSolicitacao(guiaPedro);
        
        var guiaMaria = guiaFacade.gerarGuia(maria);
        var fisioterapiaMaria = guiaFacade.adicionarItem(guiaMaria, fisioterapia, 2L);
        guiaFacade.concluirSolicitacao(guiaMaria);
        
        // Processamento de Pedidos e Notificações
        guiaFacade.indeferirGuia(guiaJose);
        
        guiaFacade.aprovarGuia(guiaPedro);
        
        guiaFacade.analisarItem(fisioterapiaMaria, 1L);
        guiaFacade.concluirAnaliseGuia(guiaMaria);
        
        var hoje = LocalDate.now();
        
        // Gerenciamento de Envio
		integracaoFacade.gerarResumoParaFolhaDoMes(empresa, hoje.getMonth(), hoje.getYear());
        
        System.out.println("Execução finalizada!");
	}

}
