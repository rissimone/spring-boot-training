package br.com.sefaz.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.sefaz.loja.Categoria;
import br.com.sefaz.loja.Produto;
import br.com.sefaz.loja.dao.CategoriaDao;
import br.com.sefaz.loja.dao.ProdutoDao;
import br.com.sefaz.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		povoarBancoDeDados();		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao( em );	
		produtoDao.buscarPorParametrosComCriteria( "Macbook", new BigDecimal( "12000" ), null);
		
		em.close();
		
				
	}
	
private static void povoarBancoDeDados() {		
		
		Categoria celulares 	= new Categoria( "CELULARES", "" );
		Categoria videogames 	= new Categoria( "VIDEOGAMES", "" );
		Categoria informatica 	= new Categoria( "INFORMATICA", "" );		
		Produto celular 		= new Produto( "Xiaomi Redmi", "Excelente", new BigDecimal( "2000" ), celulares );		
		Produto videogame 		= new Produto( "PS5", "Playstation 5", new BigDecimal( "5000" ), videogames );
		Produto macbook 		= new Produto( "Macbook", "Macbook pro", new BigDecimal( "12000" ), informatica );
		
		// persistencia
		EntityManager em 			= JPAUtil.getEntityManager();	
		ProdutoDao produtoDao 		= new ProdutoDao( em );	
		CategoriaDao categoriaDao 	= new CategoriaDao( em );
		
		System.out.println( "\niniciando transações..." );
		em.getTransaction().begin();	
				
		System.out.println( "\ncadastrando caregorias..." );
		categoriaDao.cadastrar( celulares );
		categoriaDao.cadastrar( videogames );
		categoriaDao.cadastrar( informatica );
		
		System.out.println( "\ncadastrando produtos..." );
		produtoDao.cadastrar( celular );
		produtoDao.cadastrar( videogame );
		produtoDao.cadastrar( macbook );
				
		System.out.println( "\nfinalizando transações..." );
		em.getTransaction().commit();
		em.close();		
		
		System.out.println( "\nbanco de dados povoado!" );
	}

}
