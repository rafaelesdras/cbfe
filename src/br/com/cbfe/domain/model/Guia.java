package br.com.cbfe.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.cbfe.domain.Observer;
import br.com.cbfe.domain.Subject;

public class Guia implements Subject {
	
	private Long numero;
	
	private LocalDate dataSolicitacao;
	
	private Funcionario funcionario;
	
	private List<Item> itens;
	
	private ResultadoAnalise resultadoAnalise;
	
	private LocalDate dataAnalise;
	
	private Set<Observer> observers = new HashSet<>(0);
	
	private Guia(GuiaBuilder builder) {
		this.numero = builder.numero;
		this.funcionario = builder.funcionario;
		this.itens = builder.itens == null ? new ArrayList<>(0) : builder.itens;
		registerObserver(funcionario);
	}

	public Long getNumero() {
		return numero;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void adicionarItem(Item item) {
		item.setGuia(this);
		this.itens.add(item);
	}

	public boolean removerItem(Item item) {
		return this.itens.remove(item);
	}

	public ResultadoAnalise getResultadoAnalise() {
		return resultadoAnalise;
	}

	public void setResultadoAnalise(ResultadoAnalise resultadoAnalise) {
		this.resultadoAnalise = resultadoAnalise;
		this.dataAnalise = LocalDate.now();
		notifyObservers();
	}

	public LocalDate getDataAnalise() {
		return dataAnalise;
	}
	
	public void aprovar() {
		itens.forEach(item -> {
			item.setResultadoAnalise(ResultadoAnalise.APROVADO);
			item.setQuantidadeLiberada(item.getQuantidadeSolicitada());
		});
		setResultadoAnalise(ResultadoAnalise.APROVADO);
	}
	
	public void indeferir() {
		itens.forEach(item -> {
			item.setResultadoAnalise(ResultadoAnalise.INDEFERIDO);
			item.setQuantidadeLiberada(0L);
		});
		setResultadoAnalise(ResultadoAnalise.INDEFERIDO);
	}
	
	public boolean isPendenteDeAnalise() {
		return getItens().stream().anyMatch(Item::isPendenteDeAnalise);
	}
	
	public void concluirSolicitacao() {
		this.dataSolicitacao = LocalDate.now();
		setResultadoAnalise(ResultadoAnalise.AGUARDANDO_ANALISE);
	}
	
	public String textoAlteracaoDeSituacao() {
		return new StringBuilder()
				.append("Prezado(a) ")
				.append(funcionario.getNome())
				.append(", ")
				.append("sua guia número ")
				.append(numero)
				.append(" foi alterada em ")
				.append(dataAnalise)
				.append(" para a situação ")
				.append(resultadoAnalise.name())
				.append(".")
				.toString();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (var observer : observers) {
			observer.update(this);
		}
	}
	
	public static class GuiaBuilder {

		private Long numero;
		
		private Funcionario funcionario;
		
		private List<Item> itens;
		
		public GuiaBuilder(Long numero, Funcionario funcionario) {
			this.numero = numero;
			this.funcionario = funcionario;
		}

		public GuiaBuilder setItens(List<Item> itens) {
			this.itens = itens;
			return this;
		}
		
		public Guia build(){
			return new Guia(this);
		}
	}

}
