package com.archsoft;

import com.archsoft.entity.Funcionario;
import com.archsoft.entity.Pessoa;

import javax.persistence.*;
import java.util.List;

import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("jpa-01");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			Pessoa p = new Pessoa();
			p.setNome("Jose da Silva");

			entityManager.persist(p);

			Funcionario f = new Funcionario();
			f.setNome("Joao Trabalhador");
			f.setSalario(10000.0);

			entityManager.persist(f);

			TypedQuery<Pessoa> pessoaQuery = entityManager.createQuery("select p from Pessoa p", Pessoa.class);
			List<Pessoa> pessoas = pessoaQuery.getResultList();
			pessoas.stream().forEach(out::println);
			out.printf("Quantidade de pessas: %d\n", pessoas.size());

			TypedQuery<Funcionario> funcionarioQuery = entityManager.createQuery("select f from Funcionario f", Funcionario.class);
			List<Funcionario> funcionarios = funcionarioQuery.getResultList();
			funcionarios.stream().forEach(out::println);
			out.printf("Quantidade de funcionarios: %d\n", funcionarios.size());

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
