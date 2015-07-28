package br.com.caelum.notasfiscais.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

public class DataModelNotas extends LazyDataModel<NotaFiscal> {

	@Inject private NotaFiscalDao dao;
	
	@PostConstruct void initTotal() { super.setRowCount(dao.contaTodos()); }
	
	public List<NotaFiscal> load(int start, int len, 
			String orderBy, SortOrder order, 
			Map<String, Object> filters) {
		return dao.listaTodosPaginada(start, len);
	}
	
}
