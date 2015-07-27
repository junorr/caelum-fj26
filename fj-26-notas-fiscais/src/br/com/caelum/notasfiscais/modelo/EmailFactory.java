package br.com.caelum.notasfiscais.modelo;

import javax.enterprise.inject.Produces;

public class EmailFactory {

	@Produces @EmailComercial
	private static final String emailComercial = "comercial@uberdist.com.br";

	@Produces @EmailFinanceiro
	private static final String emailFinanceiro = "financeiro@uberdist.com.br";
	
}
