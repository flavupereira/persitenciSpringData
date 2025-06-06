package br.com.flavio.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.flavio.spring.data.orm.Cargo;
import br.com.flavio.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
			     deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}

	}

	public void salvar(Scanner scanner) {
		System.out.println("Descricao do Cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o ID");
		int id = scanner.nextInt();
		
		System.out.println("Digite a descrição");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Arquivo Atulizado");

	}

	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	
	private void deletar(Scanner scanner) {
		 System.out.println("Digite o ID");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Cargo deletado");

	}

}
