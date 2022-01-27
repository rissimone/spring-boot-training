package br.com.sefaz.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.sefaz.loja.Categoria;
import br.com.sefaz.loja.Produto;
import br.com.sefaz.loja.dao.CategoriaDao;
import br.com.sefaz.loja.dao.ProdutoDao;
import br.com.sefaz.loja.util.JPAUtil;

public class CadastrarProduto {
	
	public static void main( String[] args ) {		
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();	
		ProdutoDao produtoDao = new ProdutoDao( em );	
		
		Produto p = produtoDao.buscarPorId( id );
		System.out.println( p.getPreco() );
		
		BigDecimal preco = produtoDao.buscarPrecoPorNomeDaCategoria( "Celulares" );
		System.out.println( preco );	
		
	}
	
	public static void cadastrarProduto() {
		
		Categoria celulares = new Categoria( "Celulares", "teste" );
		Produto celular 	= new Produto( 
				"Xiaomi Redmi", "Excelente", new BigDecimal( "5000" ), celulares );	
		
		EntityManager em 			= JPAUtil.getEntityManager();		
		ProdutoDao produtoDao 		= new ProdutoDao( em );	
		CategoriaDao categoriaDao 	= new CategoriaDao( em );
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar( celulares );
		produtoDao.cadastrar( celular );	
		
		em.getTransaction().commit();		
		em.close();		
	}

}
