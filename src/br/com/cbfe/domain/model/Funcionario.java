package br.com.cbfe.domain.model;

import java.time.LocalDate;
import java.time.Period;

import br.com.cbfe.domain.Observer;

public class Funcionario implements Observer {
	
	private Long matricula;
	
	private String nome;
	
	private Cargo cargo;
	
	private LocalDate dataAdmissao;
	
	private LocalDate dataDemissao;
	
	private boolean possuiDeficiencia;
	
	private FormaAcompanhamento formaAcompanhamento;
	
	public Funcionario(Long codigo, String nome, Cargo cargo, LocalDate dataAdmissao, boolean possuiDeficiencia,
			FormaAcompanhamento formaAcompanhamento) {
		setMatricula(codigo);
		setNome(nome);
		setCargo(cargo);
		setDataAdmissao(dataAdmissao);
		setPossuiDeficiencia(possuiDeficiencia);
		setFormaAcompanhamento(formaAcompanhamento);
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public boolean isPossuiDeficiencia() {
		return possuiDeficiencia;
	}

	public void setPossuiDeficiencia(boolean possuiDeficiencia) {
		this.possuiDeficiencia = possuiDeficiencia;
	}

	public FormaAcompanhamento getFormaAcompanhamento() {
		return formaAcompanhamento;
	}

	public void setFormaAcompanhamento(FormaAcompanhamento formaAcompanhamento) {
		this.formaAcompanhamento = formaAcompanhamento;
	}
	
	public int getTempoContratacaoEmAnos() {
		return Period.between(dataAdmissao, LocalDate.now()).getYears();
	}
	
	public Senioridade getSenioridade() {
		return Senioridade.getLevel(getTempoContratacaoEmAnos());
	}

	@Override
	public void update(Guia guia) {
		formaAcompanhamento.enviarNotificacao(guia.textoAlteracaoDeSituacao());
	}

}
