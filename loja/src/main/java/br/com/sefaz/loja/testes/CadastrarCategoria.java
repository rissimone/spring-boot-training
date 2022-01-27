package br.com.sefaz.loja.testes;

import javax.persistence.EntityManager;

import br.com.sefaz.loja.Categoria;
import br.com.sefaz.loja.dao.CategoriaDao;
import br.com.sefaz.loja.util.JPAUtil;

public class CadastrarCategoria {
	
	public static void main( String[] args ) {	
		
		EntityManager em 			= JPAUtil.getEntityManager();
		Categoria celulares 		= new Categoria( "Celulares", "teste" );	
		CategoriaDao categoriaDao 	= new CategoriaDao( em );
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar( celulares );
		
		em.getTransaction().commit();		
		em.close();		
		
	}
}
