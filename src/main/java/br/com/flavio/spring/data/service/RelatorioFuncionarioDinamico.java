package br.com.flavio.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.flavio.spring.data.orm.Funcionario;
import br.com.flavio.spring.data.repository.FuncionarioRepository;
import br.com.flavio.spring.data.specification.SpecficationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository FuncionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM,yyyy");
	
	public RelatorioFuncionarioDinamico (FuncionarioRepository FuncionarioRepository) {
		this.FuncionarioRepository = FuncionarioRepository;
		
	}
	
	public void inicial (Scanner scanner) {
		
		System.out.println("Digite um nome");
		String nomeString = scanner.next();
		
		if(nomeString.equalsIgnoreCase("NULL")) {
			nomeString = null;
		}
		
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		System.out.println("Digite a data de contratação");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data , formatter);
		}
		
		
		
		
		List<Funcionario> funcionarios = 
			FuncionarioRepository.findAll(Specification
			.where(
					SpecficationFuncionario.nome(nomeString))
			        .or(SpecficationFuncionario.cpf(cpf))
					.or(SpecficationFuncionario.salario(salario))
					.or(SpecficationFuncionario.dataContratacao(dataContratacao))
					);
		funcionarios.forEach(System.out::println);
			        
	}
}
