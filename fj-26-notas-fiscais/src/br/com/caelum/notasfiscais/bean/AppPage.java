package br.com.caelum.notasfiscais.bean;

public enum AppPage {
	
	LOGIN, PRODUTOS, USUARIOS;
	
	private static final String redirect = "?faces-redirect=true";
	
	public String toString() {
		return this.name().toLowerCase().concat(redirect);
	}
	
}
