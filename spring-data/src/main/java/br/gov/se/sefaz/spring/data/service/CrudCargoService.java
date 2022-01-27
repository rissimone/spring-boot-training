package br.gov.se.sefaz.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.gov.se.sefaz.spring.data.orm.Cargo;
import br.gov.se.sefaz.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService( CargoRepository cargoRepository ) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial() {
		
		Scanner scanner = new Scanner(System.in);
		
		while ( system ) {
			System.out.println( "Qual ação de cargo deseja executar?" );
			System.out.println( "0 - Sair" );
			System.out.println( "1 - Novo Cargo" );
			System.out.println( "2 - Atualizar Cargo" );
			System.out.println( "3 - Visualizar Cargo" );
			System.out.println( "4 - Deletar Cargo" );
			
			switch ( scanner.nextInt() ) {
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
	
	private void salvar() {
		Scanner scannerCargo = new Scanner(System.in); 		
		System.out.println( "Descrição do cargo" );		
		String descricao = scannerCargo.nextLine();
			
		Cargo cargo = new Cargo();
		cargo.setDescricao( descricao );
		
		cargoRepository.save( cargo );
		System.out.println( "Salvo" );			
	}
	
	private void atualizar() {		
		Scanner scannerId = new Scanner(System.in); 
		System.out.println( "Id: " );		
		int id = scannerId.nextInt();
		
		Scanner scannerNovaDescricao = new Scanner(System.in);
		System.out.println( "Nova descrição do cargo:" );
		String descricao = scannerNovaDescricao.nextLine();
		
		Cargo cargo = new Cargo();
		cargo.setId( id );
		cargo.setDescricao( descricao );

		cargoRepository.save(cargo);
		System.out.println( "Cargo atualizado!" );
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach( cargo -> System.out.println( cargo ) );
	}
	
	private void deletar() {
		Scanner scannerId = new Scanner(System.in); 
		System.out.println( "Id: " );		
		int id = scannerId.nextInt();
		
		cargoRepository.deleteById( id );
		System.out.println( "Cargo deletado!" );
	}

}
