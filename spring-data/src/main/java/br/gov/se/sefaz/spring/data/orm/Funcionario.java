package br.gov.se.sefaz.spring.data.orm;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private float salario;
	
	private LocalDate dataContratacao;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario") }, 
			inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
	private List<UnidadeTrabalho> unidadeTrabalho;
	
	public Funcionario() {
		this.dataContratacao = LocalDate.now();		
	}

	//novo funcionário
	public Funcionario(String nome, String cpf,  Cargo cargo, float salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.salario = salario;
		this.dataContratacao = LocalDate.now();
	}

	// atualizar funcionário
	public Funcionario(Long id, String nome, String cpf,  Cargo cargo, float salario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	/*
	 * public Cargo getCargo() { return cargo; }
	 * 
	 * public void setCargo(Cargo cargo) { this.cargo = cargo; }
	 */

	public List<UnidadeTrabalho> getUnidadeTrabalho() {
		return unidadeTrabalho;
	}

	public void setUnidadeTrabalho(List<UnidadeTrabalho> unidadeTrabalho) {
		this.unidadeTrabalho = unidadeTrabalho;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", salario=" + salario
				+ ", dataContratacao=" + dataContratacao + "]";
	}
}
