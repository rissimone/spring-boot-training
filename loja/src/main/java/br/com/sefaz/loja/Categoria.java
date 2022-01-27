package br.com.sefaz.loja;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "categorias" )
public class Categoria {
	
	@EmbeddedId
	private CategoriaId id;
	
	public Categoria() {

	}
	
	public Categoria( String nome, String tipo ) {
		this.id = new CategoriaId( nome, tipo );
	}
	
	public String getNome( String nome, String tipo ) {
		return id.getNome();
	}

	public void setNome( String nome ) {
		this.id.setNome( nome );
	}

	public String getTipo() {
		return id.getTipo();
	}

	public void setTipo(String tipo) {
		this.id.setTipo( tipo );
	}
	
}
