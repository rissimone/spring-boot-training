package br.gov.se.sefaz.spring.data.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.se.sefaz.spring.data.orm.Funcionario;

public class SpecificationFuncionario {
	
	public static Specification<Funcionario> nome(String nome){
		return (root, criterioQuery, criterisBuilder) -> criterisBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Funcionario> cpf(String cpf){
		return (root, criterioQuery, criterisBuilder) -> criterisBuilder.like(root.get("cpf"), "%" + cpf + "%");
	}
	
	public static Specification<Funcionario> salario(float salario){
		return (root, criterioQuery, criterisBuilder) -> criterisBuilder.like(root.get("salario"), "%" + salario + "%");
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao){
		return (root, criterioQuery, criterisBuilder) -> criterisBuilder.like(root.get("dataContratacao"), "%" + dataContratacao + "%");
	}
}
