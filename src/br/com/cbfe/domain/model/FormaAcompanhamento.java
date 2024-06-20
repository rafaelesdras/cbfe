package br.com.cbfe.domain.model;

public enum FormaAcompanhamento {
	
	SMS {
		@Override
		public void enviarNotificacao(String mensagem) {
			System.out.println("SMS: " + mensagem);
		}
	},
	E_MAIL {
		@Override
		public void enviarNotificacao(String mensagem) {
			System.out.println("E-mail: " + mensagem);
		}
	},
	NAO_ACOMPANHAR {
		@Override
		public void enviarNotificacao(String mensagem) {
			System.out.println("Não há acompanhamento.");
		}
	};
	
	abstract void enviarNotificacao(String mensagem);

}
