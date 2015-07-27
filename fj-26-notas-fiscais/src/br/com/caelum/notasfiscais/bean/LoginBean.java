package br.com.caelum.notasfiscais.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named("login")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5691869800662132153L;

	
	@Inject private UsuarioLogado logged;
	
	@Inject private UsuarioDao dao;
	
	@Inject private Event<Usuario> loginEvent;
	
	private Usuario user = new Usuario();
	
	private String message = "";
	
	
	public Usuario getUser() {
		return user;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public String logout() {
		logged.logout();
		return AppPage.LOGIN.toString();
	}
	
	
	public String login() {
		if(dao.existe(user)) {
			message = "";
			logged.setUser(user);
			loginEvent.fire(user);
			return AppPage.INDEX.toString();
		}
		message = "Credenciais Inválidas!";
		return null;
	}
	
}
