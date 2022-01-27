package br.com.sefaz.loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "pedidos" )
public class Pedido {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;	
	
	@ManyToOne( fetch = FetchType.LAZY )
	private Cliente clienteId;	
	
	@OneToMany( mappedBy = "pedido", cascade = CascadeType.ALL )
	private List<ItemPedido> itens = new ArrayList<>();	
	
	private LocalDate data = LocalDate.now();	
	
	@Column( name = "valor_total" )
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public Pedido() {
		
	}

	public Pedido
	(
		Cliente 	clientId, 
		BigDecimal 	valorTotal
	) {
		this.clienteId 		= clientId;
		this.data 			= LocalDate.now();
		this.valorTotal 	= valorTotal;
	}
	
	public Pedido( Cliente clientId ) {
		this.clienteId 		= clientId;
		this.data 			= LocalDate.now();
	}
	
	public void adicionarItem( ItemPedido item ) {
		item.setPedido( this );
		this.itens.add( item );
		this.valorTotal = this.valorTotal.add( item.getValor() );
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clientId) {
		this.clienteId = clientId;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}		
}
