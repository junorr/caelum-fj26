package br.com.caelum.notasfiscais.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named("userb")
@RequestScoped
public class UsuarioBean {
	
	public static final String
			MSG_OK = "Usuario %s com Sucesso",
			MSG_ERROR = "Erro ao %s";

	@Inject private UsuarioDao dao;
	
	private Usuario user = new Usuario();
	
	private List<Usuario> users = null;
	
	private String message = "";
	
	
	public Usuario getUser() {
		return user;
	}
	
	
	public void setUser(Usuario usr) {
		user = usr;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String str) {
		this.message = str;
	}
	
	
	public List<Usuario> getUsers() {
		if(users == null) {
			users = dao.listaTodos();
		}
		System.out.println("UsuarioBean.getUsers()");
		return users;
	}
	
	
	public void gravar() {
		if(user == null) return;
		String acao = "Gravado";
		if(user.getId() != null) {
			dao.atualiza(user);
			acao = "Atualizado";
		}
		else {
			dao.adiciona(user);
		}
		System.out.println("* UsuarioBean.gravar()");
		user = new Usuario();
		users = dao.listaTodos();
		message = String.format(MSG_OK, acao);
	}
	
	
	public void remove(Usuario usr) {
		if(usr != null) {
			dao.remove(usr);
		}
		System.out.println("* UsuarioBean.remove( "+ usr+ " )");
		user = new Usuario();
		users = dao.listaTodos();
		message = String.format(MSG_OK, "Removido");
	}
	
	
	public void clear() {
		System.out.println("* UsuarioBean.clear()");
		user = new Usuario();
		message = "";
	}
	
}
