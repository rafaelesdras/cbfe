package br.com.cbfe.domain.model;

public enum Senioridade {
	ESTAGIARIO(0, 1, Double.valueOf(0D)),
	TRAINEE(1, 2, Double.valueOf(0.25D)),
	JUNIOR(2, 5, Double.valueOf(0.3D)),
	PLENO(5, 9, Double.valueOf(0.35D)),
	SENIOR(10, Integer.MAX_VALUE, Double.valueOf(0.5D));
	
	private Integer tempoInicialNoCargoEmAnos;
	
	private Integer tempoFinalNoCargoEmAnos;
	
	private Double percentualDescontoPorAno;
	
	private Senioridade(Integer tempoInicialNoCargoEmAnos, Integer tempoFinalNoCargoEmAnos,
			Double percentualDescontoPorAno) {
		this.tempoInicialNoCargoEmAnos = tempoInicialNoCargoEmAnos;
		this.tempoFinalNoCargoEmAnos = tempoFinalNoCargoEmAnos;
		this.percentualDescontoPorAno = percentualDescontoPorAno;
	}
	
	public static Senioridade getLevel(Integer tempoNoCargoEmAnos) {
		for (var level : values()) {
			if (tempoNoCargoEmAnos > level.getTempoInicialNoCargoEmAnos() &&
					tempoNoCargoEmAnos <= level.getTempoFinalNoCargoEmAnos()) {
				return level;
			}
		}
		
		return ESTAGIARIO;
	}

	public Integer getTempoInicialNoCargoEmAnos() {
		return tempoInicialNoCargoEmAnos;
	}

	public Integer getTempoFinalNoCargoEmAnos() {
		return tempoFinalNoCargoEmAnos;
	}

	public Double getPercentualDescontoPorAno() {
		return percentualDescontoPorAno;
	}

}
