package br.com.caelum.notasfiscais.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named("nf")
@ViewScoped
public class NotaBean {

	public static final String
			MSG_OK = "%s %s com Sucesso",
			MSG_ERROR = "Erro ao %s";

	private Item item;
	
	private NotaFiscal nota;
	
	private String message;
	
	@Inject private NotaFiscalDao dao;
	
	
	public NotaBean() {
		item = new Item();
		nota = new NotaFiscal();
		message = "";
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public Item getItem() {
		return item;
	}
	
	
	public NotaFiscal getNota() {
		return nota;
	}
	
	
	public void appendItem() {
		nota.getItens().add(item);
		item.setNotaFiscal(nota);
		item.setValorUnitario(item.getProduto().getPreco());
		item = new Item();
		message = "Item Adicionado com Sucesso!";
	}
	
	
	public void clear() {
		nota = new NotaFiscal();
		item = new Item();
		message = "";
	}
	
}