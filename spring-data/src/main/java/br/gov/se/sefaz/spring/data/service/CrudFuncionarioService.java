package br.gov.se.sefaz.spring.data.service;

import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.se.sefaz.spring.data.orm.Cargo;
import br.gov.se.sefaz.spring.data.orm.Funcionario;
import br.gov.se.sefaz.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	
	private boolean system = true;
	private final FuncionarioRepository funcionarioRepository;	

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial() throws Exception {		
		Scanner scFuncionario = new Scanner(System.in);		
		while (system) {
			System.out.println( "Qual ação de Funcionário deseja executar?" );
			System.out.println( "0 - Sair" );
			System.out.println( "1 - Novo Funcionário" );
			System.out.println( "2 - Atualizar Funcionário" );
			System.out.println( "3 - Visualizar Funcionário" );
			System.out.println( "4 - Deletar Funcionário" );
			
			switch (scFuncionario.nextInt()) {
				case 0:	
					system = false;
					break;
				case 1:
					salvar();
					break;
				case 2:
					atualizar();
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar();
					break;	
				default:
					system = false;
					break;
			}
		}		
	}

	private void deletar() {
		Scanner scannerId = new Scanner(System.in); 
		System.out.println( "Digite o Id do funcionário que deseja deletar: " );		
		long id = scannerId.nextInt();
		
		funcionarioRepository.deleteById(id);
		System.out.println( "Os dados do fucionário foram deletados!" );
	}

	private void visualizar() {
		Scanner scannerPage = new Scanner(System.in); 
		System.out.println("Qual página vc deseja visualizar?");
		Integer page = scannerPage.nextInt();
		
		Pageable pageable = PageRequest.of( (page-1), 5, Sort.by(Sort.Direction.ASC, "nome") );		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual: " + funcionarios.getNumber()+1 );
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
						
		if (funcionarios.getTotalElements() > 0) {
			funcionarios.forEach( funcionario -> System.out.println( funcionario ));
			System.out.println( "\n" );
		} else {
			System.out.println( "Nenhum funcionário encontrado nesta página!" );
		}
	}

	private void atualizar() {		
		Scanner scannerId = new Scanner(System.in); 
		System.out.println( "Id do Funcionário a ser atualizado: " );
		long id = scannerId.nextInt();
		
		Scanner scannerNome = new Scanner(System.in); 		
		System.out.println( "Nome do Funcionário: " );		
		String nome = scannerNome.nextLine();
		
		Scanner scannerCPF = new Scanner(System.in);
		System.out.println( "CPF do Funcionário: " );		
		String cpf = scannerCPF.nextLine();
		
		Scanner scannerCargo = new Scanner(System.in);
		System.out.println( "Cargo do Funcionário: " );		
		Cargo cargo = new Cargo();
		cargo.setId(scannerCargo.nextInt());		
		
		Scanner scannerSalario = new Scanner(System.in);
		System.out.println( "Salário do Funcionário: " );		
		float salario = scannerSalario.nextFloat();
		
		Funcionario funcionario = new Funcionario(id, nome, cpf, cargo, salario);
		funcionarioRepository.save(funcionario);
		System.out.println( "Os dados do funcionário foram atualizados!" );		
	}

	private void salvar() {
		Scanner scannerNome = new Scanner(System.in); 		
		System.out.println( "Nome do Funcionário: " );		
		String nome = scannerNome.nextLine();
		
		Scanner scannerCPF = new Scanner(System.in);
		System.out.println( "CPF do Funcionário: " );		
		String cpf = scannerCPF.nextLine();
		
		Scanner scannerCargo = new Scanner(System.in);
		System.out.println( "Cargo do Funcionário: " );		
		Cargo cargo = new Cargo();
		cargo.setId(scannerCargo.nextInt());
		
		Scanner scannerSalario = new Scanner(System.in);
		System.out.println( "Salário do Funcionário: " );		
		float salario = scannerSalario.nextFloat();
			
		Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario);	
		funcionarioRepository.save( funcionario );
		System.out.println( "Os dados do funcionário foram salvos!" );			
	}

}
