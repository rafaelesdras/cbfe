package br.com.cbfe.domain.model;

public class Cargo {
	
	private Long codigo;
	
	private String nome;
	
	private Empresa empresa;
	
	private Double percentualCoparticipacao;
	
	public Cargo(Long codigo, String nome, Empresa empresa, Double percentualCoparticipacao) {
		setCodigo(codigo);
		setNome(nome);
		setEmpresa(empresa);
		setPercentualCoparticipacao(percentualCoparticipacao);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPercentualCoparticipacao() {
		return percentualCoparticipacao;
	}

	public void setPercentualCoparticipacao(Double percentualCoparticipacao) {
		this.percentualCoparticipacao = percentualCoparticipacao;
	}

}
