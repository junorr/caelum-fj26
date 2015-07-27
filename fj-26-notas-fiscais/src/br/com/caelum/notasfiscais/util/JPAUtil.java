package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		EntityManager manager = emf.createEntityManager();
		//manager.getTransaction().begin();
		return manager;
	}
	
	
	public void close(@Disposes EntityManager manager) {
		if(manager != null) {
			//manager.getTransaction().commit();
			manager.close();
		}
	}
	
}