package br.com.caelum.notasfiscais.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
@SessionScoped
public class ProdutoBean {
	
	public static final String
			MSG_OK = "Produto Gravado com Sucesso",
			MSG_RM_OK = "Produto Removido com Sucesso",
			MSG_ERROR = "Erro ao Gravar no Banco de Dados";

	private ProdutoDao dao = new ProdutoDao();
	
	private Produto produto = new Produto();
	
	private List<Produto> produtos = null;
	
	private String message = "";
	
	
	public Produto getProduto() {
		return produto;
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
	
	
	public void grava() {
		dao.adiciona(produto);
		produto = new Produto();
		produtos = dao.listaTodos();
		message = MSG_OK;
	}
	
	
	public void remove(Produto pd) {
		if(pd != null) {
			dao.remove(pd);
		}
		produtos = dao.listaTodos();
		message = MSG_RM_OK;
	}
	
}