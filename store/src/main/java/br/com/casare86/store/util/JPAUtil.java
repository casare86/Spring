package br.com.casare86.store.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final EntityManagerFactory EMFACTORY = Persistence
			.createEntityManagerFactory("store");
	
	public static EntityManager getEntityManager() {
		return EMFACTORY.createEntityManager();
	}

}
