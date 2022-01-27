package br.gov.se.sefaz.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.gov.se.sefaz.spring.data.orm.Funcionario;
import br.gov.se.sefaz.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
	
	private final FuncionarioRepository funcionarioRepository;	
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial() {
		
		Scanner scanner = new Scanner(System.in);
		
		while ( system ) {
			System.out.println( "Qual ação de cargo deseja executar?" );
			System.out.println( "0 - Sair" );
			System.out.println( "1 - Busca Funcionário por nome" );
			System.out.println( "2 - Busca Funcionario por nome, data de admissão e salário.");
			System.out.println( "3 - Busca Funcionario por data de admissão.");
			
			switch ( scanner.nextInt() ) {
				case 0:
					system = false;
					break;
				case 1:
					buscarNome();
					break;
				case 2:
					buscarFuncionarioNomeSalarioMariorData();
					break;
				case 3:
					buscarFuncionarioDataContratacao();
					break;
				default:
					system = false;
					break;
			}
		}		
	}

	private void buscarNome() {
		Scanner scannerNome = new Scanner(System.in);
		System.out.println("Qual nome deseja pesquisar?");		
		String nome = scannerNome.next();
		
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		
		if(list.size() == 0) {
			System.out.println("Nenhum resultado encontrado.\n");
		} else {
			list.forEach(System.out::println);
			System.out.println("\n");
		}
	}
	
	private void buscarFuncionarioNomeSalarioMariorData() {
		
		System.out.println("Buscar Funcionário com maior salário.");
		System.out.println("Insira os termos de busca:");
		
		Scanner scannerNome = new Scanner(System.in);
		System.out.println("Nome: ");
		String nome = scannerNome.next();
		
		Scanner scannerSalario = new Scanner(System.in);
		System.out.println("Salário:");
		float salario = scannerSalario.nextFloat();
		
		Scanner scannerData = new Scanner(System.in);
		System.out.println("Data em DD/MM/AAAA:");
		String data = scannerData.next();	
		LocalDate localData = LocalDate.parse(data, formatter);
				
		List<Funcionario> list = funcionarioRepository.findSalarioMaior(nome, salario, localData);
		
		if(list.size() == 0) {
			System.out.println("Nenhum resultado encontrado.\n");
		} else {
			list.forEach(System.out::println);
			System.out.println("\n");
		}		
	}
	
	private void buscarFuncionarioDataContratacao() {		
		Scanner scannerData = new Scanner(System.in);
		System.out.println("Data em DD/MM/AAAA:");
		String data = scannerData.next();	
		LocalDate ldData = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findContratacaoMaior(ldData);		
		if(list.size() == 0) {
			System.out.println("Nenhum resultado encontrado.\n");
		} else {
			list.forEach(System.out::println);
			System.out.println("\n");
		}		
	}

}
