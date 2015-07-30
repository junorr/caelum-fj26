package br.com.caelum.notasfiscais.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.modelo.ViewModel;

@Named("nf")
@ViewModel
public class NotaBean implements Serializable {

	private static final long serialVersionUID = -8384996687924076506L;

	public static final String
			MSG_OK = "%s %s com Sucesso",
			MSG_ERROR = "Erro ao %s";

	private Item item;
	
	//private Long idProduto;
	
	private NotaFiscal nota;
	
	private String message;
	
	private boolean editItem, editNota;
	
	private int indexItem;
	
	@Inject private transient ProdutoDao pdao;
	
	@Inject private transient NotaFiscalDao dao;
	
	@Inject private DataModelNotas notas;
	
	
	public NotaBean() {
		item = new Item();
		nota = new NotaFiscal();
		message = "";
		editItem = false;
		editNota = false;
		indexItem = -1;
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
	
	
	public DataModelNotas getDataModel() {
		return notas;
	}
	
	
	public boolean isEditItem() {
		return editItem;
	}
	
	
	public void editItem(Item it) {
		if(it == null) return;
		item = it;
		indexItem = nota.getItens().indexOf(it);
		//idProduto = item.getProduto().getId();
		editItem = true;
	}
	
	
	public void editNota(NotaFiscal nf) {
		if(nf == null) return;
		nota = nf;
		editNota = true;
	}
	
	
	public void clearItem() {
		item = new Item();
		editItem = false;
		message = "";
	}
	
	
	public void removeNota(NotaFiscal nf) {
		if(nf == null) return;
		dao.remove(nf);
		message = "Nota Fiscal Removida com Sucesso!";
	}
	
	
	public void removeItem(Item it) {
		if(it == null) return;
		nota.getItens().remove(it);
		message = "Item Removido com Sucesso!";
	}
	
	
	public void appendItem() {
		//Produto p = pdao.buscaPorId(idProduto);
		if(editItem) {
			nota.getItens().remove(indexItem);
			item.setId(null);
		}
		//item.setProduto(p);
		item.setNotaFiscal(nota);
		item.setValorUnitario(item.getProduto().getPreco());
		nota.getItens().add(item);
		item = new Item();
		editItem = false;
		message = "Item Adicionado com Sucesso!";
	}
	
	
	public void gravarNota() {
		if(editNota) {
			dao.atualiza(nota);
		}
		else {
			dao.adiciona(nota);
		}
		message = "Nota Fiscal Gravada com Sucesso!";
		nota = new NotaFiscal();
		item = new Item();
		editItem = false;
		indexItem = -1;
		editNota = false;
	}
	
	
	public void clear() {
		message = " ";
		nota = new NotaFiscal();
		item = new Item();
		editItem = false;
	}
	
}
