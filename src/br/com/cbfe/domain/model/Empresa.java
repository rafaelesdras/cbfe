package br.com.cbfe.domain.model;

public class Empresa {
	
	private Long codigo;
	
	private String nome;
	
	public Empresa(Long codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
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

}
