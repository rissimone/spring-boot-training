package br.gov.se.sefaz.spring.data.service;

import java.util.Collection;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.gov.se.sefaz.spring.data.orm.UnidadeTrabalho;
import br.gov.se.sefaz.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial() {
		Scanner scUnidadeTrabalho = new Scanner(System.in);
		while(system) {
			System.out.println( "Qual ação de Unidade de Trabalho deseja executar?" );
			System.out.println( "0 - Sair" );
			System.out.println( "1 - Nova Unidade de Trabalho" );
			System.out.println( "2 - Atualizar Unidade de Trabalho" );
			System.out.println( "3 - Visualizar Unidade de Trabalho" );
			System.out.println( "4 - Deletar Unidade de Trabalho" );
			
			switch (scUnidadeTrabalho.nextInt()) {
				case 0:		
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
		System.out.println( "Digite o Id da Unidade de Trabalho que deseja deletar: " );		
		long id = scannerId.nextInt();
		
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println( "Os dados da Unidade de Trabalho foram deletados!" );
	}

	private void visualizar() {
		int iterSize = 0;
		Iterable<UnidadeTrabalho> unidadesTrabalho = unidadeTrabalhoRepository.findAll();
		iterSize = ((Collection<?>) unidadesTrabalho).size();
		
		if (iterSize > 0)
			unidadesTrabalho.forEach( unidadeTrabalho -> System.out.println( unidadeTrabalho ));			
		else 
			System.out.println( "Nenhuma Unidade Trabalho encontrada!" );		
	}

	private void atualizar() {
		Scanner scannerId = new Scanner(System.in);
		System.out.println( "Id da Unidade de Trabalho que deseja editar: " );		
		Long id = scannerId.nextLong();
		
		Scanner scannerDescricao = new Scanner(System.in);
		System.out.println( "Descrição da Unidade de Trabalho: " );		
		String descricao = scannerDescricao.nextLine();
		
		Scanner scannerEndereco = new Scanner(System.in);
		System.out.println( "Endereço da Unidade de Trabalho: " );		
		String endereco = scannerEndereco.nextLine();
			
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(id, descricao, endereco);	
		
		unidadeTrabalhoRepository.save( unidadeTrabalho );
		System.out.println( "Os dados da Unidade de Trabalho foram atualizados!" );		
	}

	private void salvar() {		
		Scanner scannerDescricao = new Scanner(System.in);
		System.out.println( "Descrição da Unidade de Trabalho: " );		
		String descricao = scannerDescricao.nextLine();
		
		Scanner scannerEndereco = new Scanner(System.in);
		System.out.println( "Endereço da Unidade de Trabalho: " );		
		String endereco = scannerEndereco.nextLine();
			
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao, endereco);	
		
		unidadeTrabalhoRepository.save( unidadeTrabalho );
		System.out.println( "Os dados da Unidade de Trabalho foram salvos!" );
	}


}
