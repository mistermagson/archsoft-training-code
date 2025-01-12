package com.archsoft;

import com.archsoft.entity.Endereco;
import com.archsoft.entity.Pessoa;
import com.archsoft.entity.Telefone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("jpa-01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        long pessoaId = 0;

        try {
            transaction.begin();

            Pessoa p = new Pessoa();
            p.setNome("Jose da Silva");

            Telefone tel = new Telefone();
            tel.setPessoa(p);
            tel.setPais(55);
            tel.setArea(61);
            tel.setTelefone(30273045L);

            p.getTelefones().add(tel);

            Telefone cel = new Telefone();
            cel.setPessoa(p);
            tel.setPais(55);
            cel.setArea(61);
            cel.setTelefone(99801234L);

            p.getTelefones().add(cel);

            Endereco e = new Endereco();
            e.setRua("Araucaria");
            e.setCidade("Brasilia");
            e.setEstado("DF");
            e.setCep("88000000");

            p.setEndereco(e);

            entityManager.persist(p);

            pessoaId = p.getId();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try {
            transaction.begin();

            //Endereco orfao
            Pessoa p = entityManager.find(Pessoa.class, pessoaId);
			/*p.setEndereco(null);
			p.getTelefones().clear();*/

            p.getTelefones().forEach(System.out::println);

            entityManager.remove(p);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
