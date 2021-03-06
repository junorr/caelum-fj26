package br.com.caelum.notasfiscais.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.JSFUtil;

@Named
@RequestScoped
public class ProdutoBean {
	
	public static final String
			MSG_OK = "Produto %s com Sucesso",
			MSG_ERROR = "Erro ao %s";

	@Inject private ProdutoDao dao;
	
	@Inject private JSFUtil jsfutil;
	
	private Produto produto = new Produto();
	
	private List<Produto> produtos = null;
	
	private String message = "";
	
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void loadProduto() {
		if(id != null) {
			produto = dao.buscaPorId(id);
		}
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	
	
	public void setProduto(Produto pd) {
		produto = pd;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String str) {
		this.message = str;
	}
	
	
	public List<Produto> getProdutos() {
		if(produtos == null) {
			produtos = dao.listaTodos();
		}
		System.out.println("ProdutoBean.getProdutos()");
		return produtos;
	}
	
	
	public void comecaComMaiuscula(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
		if(!value.toString().matches("[A-Z].*")) {
			throw new ValidatorException(
					new FacesMessage("Nome do produto deve começar com maiúscula")
			); 
		}
	}
	
	
	public void gravar() {
		if(produto == null) return;
		String acao = "Gravado";
		if(produto.getId() != null) {
			dao.atualiza(produto);
			acao = "Atualizado";
		}
		else {
			dao.adiciona(produto);
		}
		System.out.println("* ProdutoBean.gravar()");
		produto = new Produto();
		produtos = dao.listaTodos();
		message = String.format(MSG_OK, acao);
	}
	
	
	public void remove(Produto pd) {
		if(pd != null) {
			dao.remove(pd);
		}
		System.out.println("* ProdutoBean.remove( "+ pd+ " )");
		produto = new Produto();
		produtos = dao.listaTodos();
		jsfutil.addMessage("Produto Removido com sucesso");
		message = String.format(MSG_OK, "Removido");
	}
	
	
	public void clear() {
		System.out.println("* ProdutoBean.clear()");
		produto = new Produto();
		message = "";
	}
	
}
