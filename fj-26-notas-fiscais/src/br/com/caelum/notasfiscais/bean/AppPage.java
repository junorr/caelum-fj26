package br.com.caelum.notasfiscais.bean;

public enum AppPage {
	
	LOGIN, PRODUTOS, USUARIOS, INDEX;
	
	private static final String redirect = "?faces-redirect=true";
	
	public String toString() {
		return this.name().toLowerCase().concat(redirect);
	}
	
}
