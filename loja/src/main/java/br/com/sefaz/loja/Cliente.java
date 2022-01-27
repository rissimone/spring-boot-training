package br.com.sefaz.loja;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "clientes" )
public class Cliente {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;	
	
	@Embedded
	private DadosPessoais dadosPessoais;
	
	public Cliente() {
		
	}

	public Cliente( String nome, String cpf ) {
		this.dadosPessoais = new DadosPessoais( nome, cpf );
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais( DadosPessoais dadosPessoais ) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public String getNome() {
		return this.dadosPessoais.getNome();
	}
	
	public void setNome( String nome ) {
		this.dadosPessoais.setNome( nome );
	}
	
	public String getCpf() {
		return this.dadosPessoais.getCpf();
	}
	
	public void setCpf( String cpf ) {
		this.dadosPessoais.setCpf( cpf );
	}
	
}
