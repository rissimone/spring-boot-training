package br.gov.se.sefaz.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.gov.se.sefaz.spring.data.orm.Funcionario;
import br.gov.se.sefaz.spring.data.repository.FuncionarioRepository;
import br.gov.se.sefaz.spring.data.specifications.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial() {
		System.out.print("Digite o nome: ");
		Scanner scannerNome = new Scanner(System.in);
		String nome = scannerNome.next();
		
		if(nome.equalsIgnoreCase(nome))
			nome = null;
		
		System.out.print("Digite o cpf: ");
		Scanner scannerCpf = new Scanner(System.in);
		String cpf = scannerCpf.next();
		
		if(cpf.equalsIgnoreCase(cpf))
			cpf = null;
		
		System.out.print("Digite o salario: ");
		Scanner scannerSalario = new Scanner(System.in);
		float salario = scannerSalario.nextFloat();
		
		if(salario == 0)
			salario = 0;
		
		System.out.print("Digite a data de contratação: ");
		Scanner scannerContratacao = new Scanner(System.in);
		String data = scannerContratacao.next();		
		LocalDate dataContratacao; 
		
		if(data.equalsIgnoreCase(data)) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> list = funcionarioRepository.findAll(
				Specification.where(
					SpecificationFuncionario.nome(nome))
					.or(SpecificationFuncionario.cpf(cpf))
					.or(SpecificationFuncionario.salario(salario))
					.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);		
		
		System.out.println();
	}

}
