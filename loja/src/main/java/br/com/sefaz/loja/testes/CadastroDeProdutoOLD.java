package br.com.sefaz.loja.testes;

import javax.persistence.EntityManager;

import br.com.sefaz.loja.Categoria;
import br.com.sefaz.loja.util.JPAUtil;

public class CadastroDeProdutoOLD {
	
	public static void main( String[] args ) {
		
		Categoria celulares = new Categoria( "Celulares", "" );
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist( celulares );
		celulares.setNome( "XIAOMI" );	
		
		em.flush();
		em.clear();		
		
		celulares = em.merge(celulares);
		celulares.setNome("XPTO");
		em.flush();
		em.remove(celulares);
		em.flush();
		
	}

}
