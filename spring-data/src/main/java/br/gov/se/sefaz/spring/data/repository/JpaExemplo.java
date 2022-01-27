package br.gov.se.sefaz.spring.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaExemplo {
	
	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public JpaExemplo() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();		
	}
	
	

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
	

}
