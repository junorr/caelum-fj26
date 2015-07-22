package br.com.caelum.notasfiscais.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.notasfiscais.modelo.Usuario;

@Named("logged")
@SessionScoped
public class UsuarioLogado implements Serializable {

	private Usuario user;
	
	
	public UsuarioLogado() {
		user = new Usuario();
	}
	
	
	public Usuario getUser() {
		return user;
	}
	
	
	public void setUser(Usuario user) {
		if(user != null)
			this.user = user;
	}
	
	
	public void logout() {
		this.setUser(new Usuario());
	}
	
	
	public boolean isLogged() {
		return user.getLogin() != null;
	}
	
}
