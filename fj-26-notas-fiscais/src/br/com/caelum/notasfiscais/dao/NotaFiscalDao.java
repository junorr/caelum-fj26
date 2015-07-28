package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

public class NotaFiscalDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private EntityManager manager;

	
	@Transacao
	public void adiciona(NotaFiscal nota) {
		manager.persist(nota);
	}

	
	@Transacao
	public void remove(NotaFiscal nf) {
		manager.remove(manager.merge(nf));
	}
	
	
	@Transacao
	public void atualiza(NotaFiscal nf) {
		manager.merge(nf);
	}

	
	public List<NotaFiscal> listaTodos() {
		CriteriaQuery<NotaFiscal> query = manager.getCriteriaBuilder().createQuery(NotaFiscal.class);
		query.select(query.from(NotaFiscal.class));

		List<NotaFiscal> lista = manager.createQuery(query).getResultList();

		return lista; 
	}
	
	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from NotaFiscal n").getSingleResult();
		
		return (int) result;
	}

	public List<NotaFiscal> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<NotaFiscal> query = manager.getCriteriaBuilder().createQuery(NotaFiscal.class);
		query.select(query.from(NotaFiscal.class));

		List<NotaFiscal> lista = manager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}

}
