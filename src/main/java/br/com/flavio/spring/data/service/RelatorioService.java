package br.com.flavio.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.flavio.spring.data.orm.Funcionario;
import br.com.flavio.spring.data.orm.FuncionarioProjecao;
import br.com.flavio.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	
	
	public RelatorioService(FuncionarioRepository funcionarioRepository) {

		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual ação de Funcionario deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratação e salario maior");
			System.out.println("3 - Busca Funcionario data contratação");
			System.out.println("4 - Pesquisa Funcionario Salario");
			
			

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContracao(scanner);
				break;
			case 4:
				pesquisafuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}

	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome deseja pesquisar");
		String nome = scanner.next();
		
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual o nome deseja pesquisar");
		String nome = scanner.next();
		
		System.out.println("Qual data contratação deseja pesquisar");
		String data = scanner.next();
		LocalDate localdate = LocalDate.parse(data , formatter);
		
		System.out.println("Qual salario deseja pesquisar");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localdate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContracao(Scanner scanner) {
		System.out.println("Qual data contratação deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data , formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContracaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisafuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() 
		+ " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
	
}
