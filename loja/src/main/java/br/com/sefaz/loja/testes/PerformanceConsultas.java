package br.com.sefaz.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.sefaz.loja.Categoria;
import br.com.sefaz.loja.Cliente;
import br.com.sefaz.loja.ItemPedido;
import br.com.sefaz.loja.Pedido;
import br.com.sefaz.loja.Produto;
import br.com.sefaz.loja.dao.CategoriaDao;
import br.com.sefaz.loja.dao.ClienteDao;
import br.com.sefaz.loja.dao.PedidoDao;
import br.com.sefaz.loja.dao.ProdutoDao;
import br.com.sefaz.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		povoarBancoDeDados();		
		EntityManager em = JPAUtil.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao( em );		
		Pedido pedido = pedidoDao.buscarPedidoComCliente( 1l );
		
		em.close();
		
		System.out.println( pedido.getClienteId().getNome() );
				
	}
	
private static void povoarBancoDeDados() {		
		
		Categoria celulares 	= new Categoria( "CELULARES", "" );
		Categoria videogames 	= new Categoria( "VIDEOGAMES", "" );
		Categoria informatica 	= new Categoria( "INFORMATICA", "" );
		
		Produto celular 	= new Produto( "Xiaomi Redmi", "Excelente", new BigDecimal( "2000" ), celulares );		
		Produto videogame 	= new Produto( "PS5", "Playstation 5", new BigDecimal( "5000" ), videogames );
		Produto macbook 	= new Produto( "Macbook", "Macbook pro", new BigDecimal( "12000" ), informatica );
		
		Cliente cliente = new Cliente( "Simone", "123456" );
		
		Pedido pedido = new Pedido( cliente );		
		pedido.adicionarItem( new ItemPedido( 10, pedido, celular ) );	
		pedido.adicionarItem( new ItemPedido( 40, pedido, videogame ) );
		
		Pedido pedido2 = new Pedido( cliente );
		pedido2.adicionarItem( new ItemPedido( 2, pedido2, macbook ) );		
		
		// persistencia
		EntityManager 	em 				= JPAUtil.getEntityManager();	
		ProdutoDao 		produtoDao 		= new ProdutoDao( em );	
		CategoriaDao 	categoriaDao 	= new CategoriaDao( em );
		ClienteDao 		clienteDao		= new ClienteDao( em );			
		PedidoDao 		pedidoDao 		= new PedidoDao( em );
		
		em.getTransaction().begin();	
				
		categoriaDao.cadastrar( celulares );
		categoriaDao.cadastrar( videogames );
		categoriaDao.cadastrar( informatica );
		
		produtoDao.cadastrar( celular );
		produtoDao.cadastrar( videogame );
		produtoDao.cadastrar( macbook );
		
		clienteDao.cadastrar( cliente );
		
		pedidoDao.cadastrar( pedido );
		pedidoDao.cadastrar( pedido2 );
		
		em.getTransaction().commit();
		em.close();		
	}

}
