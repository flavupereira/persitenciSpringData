package br.com.flavio.spring.data.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.flavio.spring.data.orm.Funcionario;

public class SpecficationFuncionario {

	public static Specification<Funcionario> nome(String nome) {

		return (root, CriteriaQuery, CriteriaBuilder)
			-> CriteriaBuilder.like(root.get("nome"), "%" + nome + "%");

	}

	public static Specification<Funcionario> cpf(String cpf) {

		return (root, CriteriaQuery, CriteriaBuilder)
				-> CriteriaBuilder.equal(root.get("cpf"), cpf );

	}

	public static Specification<Funcionario> salario(Double salario) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.get("salario"), salario);
	}
	

	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {

		return (root, CriteriaQuery, CriteriaBuilder) 
		-> CriteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);

	}
}
