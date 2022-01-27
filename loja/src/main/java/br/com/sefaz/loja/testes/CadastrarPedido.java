package br.com.sefaz.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.sefaz.loja.vo.RelatorioVendasVo;


public class CadastrarPedido {

	public static void main( String[] args ) {			
		popularBancoDeDados();
		
		EntityManager em 		= JPAUtil.getEntityManager();
		ProdutoDao produtoDao 	= new ProdutoDao( em );	
		ClienteDao clienteDao	= new ClienteDao( em );	
		
		Produto produto 	= produtoDao.buscarPorId(1l);	
		Produto produto2 	= produtoDao.buscarPorId(2l);
		Produto produto3 	= produtoDao.buscarPorId(3l);
		Cliente cliente 	= clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();	
				
		Pedido pedido = new Pedido( cliente );		
		pedido.adicionarItem( new ItemPedido( 10, pedido, produto ) );	
		pedido.adicionarItem( new ItemPedido( 40, pedido, produto2 ) );
		
		Pedido pedido2 = new Pedido( cliente );
		pedido2.adicionarItem( new ItemPedido( 2, pedido2, produto3 ) );
		
		PedidoDao pedidoDao = new PedidoDao( em );
		pedidoDao.cadastrar( pedido );
		pedidoDao.cadastrar( pedido2 );
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println( "VALOR TOTAL: " + totalVendido );
		
		List< RelatorioVendasVo > relatorio = pedidoDao.relatorioVendas();		
		relatorio.forEach( System.out::println );
		
	}

	private static void popularBancoDeDados() {		
		
		Categoria celulares 	= new Categoria( "CELULARES", "" );
		Categoria videogames 	= new Categoria( "VIDEOGAMES", "" );
		Categoria informatica 	= new Categoria( "INFORMATICA", "" );
		
		Produto celular 	= new Produto(
				"Xiaomi Redmi", "Excelente", new BigDecimal( "2000" ), celulares );		
		Produto videogame 	= new Produto(
				"PS5", "Playstation 5", new BigDecimal( "5000" ), videogames );
		Produto macbook 	= new Produto(
				"Macbook", "Macbook pro", new BigDecimal( "12000" ), informatica );
		
		Cliente cliente = new Cliente( "Simone", "123456" );
		
		EntityManager 	em 				= JPAUtil.getEntityManager();	
		ProdutoDao 		produtoDao 		= new ProdutoDao( em );	
		CategoriaDao 	categoriaDao 	= new CategoriaDao( em );
		ClienteDao 		clienteDao		= new ClienteDao( em );		
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar( celulares );
		categoriaDao.cadastrar( videogames );
		categoriaDao.cadastrar( informatica );
		
		produtoDao.cadastrar( celular );
		produtoDao.cadastrar( videogame );
		produtoDao.cadastrar( macbook );
		
		clienteDao.cadastrar( cliente );
		
		em.getTransaction().commit();
		em.close();		
	}
}
