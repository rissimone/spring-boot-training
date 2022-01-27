package br.gov.se.sefaz.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.gov.se.sefaz.spring.data.orm.Funcionario;
import br.gov.se.sefaz.spring.data.orm.FuncionarioProjecao;

//public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {

	//Derived Query
	List<Funcionario> findByNome(String nome);
	
	//JPQL
	// usa nome e atributos da classe, não da tabela
	@Query("select f from Funcionario f where f.nome = :nome and f.salario >= :salario and f.dataContratacao = :data")
	List<Funcionario> findSalarioMaior(String nome, float salario, LocalDate data);	
	
	//Native Query
	@Query(value = "select * from funcionarios f where f.data_contratacao >= :data", nativeQuery = true )
	List<Funcionario> findContratacaoMaior(LocalDate data);	
	
	@Query(value = "select f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}