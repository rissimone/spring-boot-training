package br.com.sefaz.loja;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto {
	
	private String marca;
	
	private int modelo;

	public Informatica() {

	}
	
	public Informatica( String marca, int modelo ) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getModelo() {
		return modelo;
	}

	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	
		

}
