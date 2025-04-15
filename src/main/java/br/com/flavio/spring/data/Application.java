package br.com.flavio.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.flavio.spring.data.service.CrudCargoService;
import br.com.flavio.spring.data.service.CrudFuncionarioService;
import br.com.flavio.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.flavio.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.flavio.spring.data.service.RelatorioService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public Application(CrudCargoService cargoService, CrudFuncionarioService crudFuncionarioService,
			CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatorioService relatorioService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {

		this.cargoService = cargoService;
		this.funcionarioService = crudFuncionarioService;
		this.unidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio dinamico");

			Integer function = scanner.nextInt();

			switch (function) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamico.inicial(scanner);
				break;
			default:
				System.out.println("Finalizando");
				system = false;
				break;
//		Cargo cargo = new Cargo();
//		cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");
//		service.save(cargo);
			}

		}
	}
}
