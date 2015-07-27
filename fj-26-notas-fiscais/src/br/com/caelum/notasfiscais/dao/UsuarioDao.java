package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.util.JPAUtil;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject private EntityManager manager;
	
	
	public boolean existe(Usuario usuario) {
		Query query = manager.createQuery("select u from Usuario u where u.login = :pLogin and u.senha = :pSenha")
						.setParameter("pLogin", usuario.getLogin())
						.setParameter("pSenha", usuario.getSenha());

		return !query.getResultList().isEmpty();
	}


	@Transacao
	public void adiciona(Usuario usr) {
		manager.persist(usr);
	}


	@Transacao
	public void remove(Usuario usr) {
		manager.remove(manager.merge(usr));
	}

	
	@Transacao
	public void atualiza(Usuario usr) {
		manager.merge(usr);
	}

	
	public List<Produto> buscaPorNome(String nome) {
		String jpql = "select u from Usuario u where "
				+ " lower(u.login) like :nome order by u.login";

		return manager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome + "%").getResultList();
	}

	
	public List<Usuario> listaTodos() {
		CriteriaQuery<Usuario> query = manager.getCriteriaBuilder().createQuery(Usuario.class);
		query.select(query.from(Usuario.class));

		return manager.createQuery(query).getResultList();
	}
	
	
	public Usuario buscaPorId(Long id) {
		return manager.find(Usuario.class, id);
	}

}