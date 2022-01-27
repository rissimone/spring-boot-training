package br.gov.se.sefaz.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.se.sefaz.spring.data.orm.UnidadeTrabalho;
import br.gov.se.sefaz.spring.data.service.CrudCargoService;
import br.gov.se.sefaz.spring.data.service.CrudFuncionarioService;
import br.gov.se.sefaz.spring.data.service.CrudUnidadeTrabalhoService;
import br.gov.se.sefaz.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService; 
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	
	private Boolean system = true;
	
	public SpringDataApplication
	(
			CrudCargoService cargoService, 
			CrudFuncionarioService funcionarioService, 
			CrudUnidadeTrabalhoService unidadeTrabalhoService,
			RelatoriosService relatoriosService
	) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
		Scanner scanner = new Scanner( System.in );
		while( system ) {		    	
			System.out.println( "Qual ação você deseja executar?" );
			System.out.println( "0 - Sair" );
			System.out.println( "1 - Cargo" );
			System.out.println( "2 - Funcionário" );
			System.out.println( "3 - Unidade de trabalho" );
			System.out.println( "4 - Relatórios" );

			int action = scanner.nextInt();
			
			if (action == 0) {
				system = false;
			} else if (action == 1) {
				cargoService.inicial();
			} else if (action == 2) {
				funcionarioService.inicial();
			} else if (action == 3) {
				unidadeTrabalhoService.inicial();
			} else if (action == 4) {
				relatoriosService.inicial();
			}
		}
	}
}
