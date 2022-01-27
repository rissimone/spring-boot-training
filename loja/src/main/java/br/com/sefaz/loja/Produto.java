package br.com.sefaz.loja;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "produtos" )
@NamedQuery( name = "produtosPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.id.nome = ?1" )
//@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Inheritance( strategy = InheritanceType.JOINED )
public class Produto {

	@Id // especifica o tributo que é chave primária no bd
	@GeneratedValue( strategy = GenerationType.IDENTITY ) // define q o atributo é gerado pelo bando e qual estratégia o banco deve usar
	private Long id;	
	
	@Column( name = "descricao" ) // especifica o nome da coluna no banco de dados
	private String descricao;
	
	@ManyToOne( fetch = FetchType.LAZY )
	private Categoria categoria;
	
	private LocalDate dataCadastro = LocalDate.now();
	
	private String nome;	
	
	private BigDecimal preco;
	
	public Produto
	(
		String 		nome, 
		String 		descricao, 
		BigDecimal 	preco, 
		Categoria 	categoria
	) {
		this.nome 		= nome;
		this.descricao 	= descricao;
		this.preco 		= preco;
		this.categoria 	= categoria;
	}

	public Produto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco( BigDecimal preco ) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro( LocalDate dataCadastro ) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria( Categoria categoria ) {
		this.categoria = categoria;
	}
	
}
