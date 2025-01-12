package com.archsoft;

import com.archsoft.model.TravelProfile;
import com.archsoft.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TravelProfile travelProfile = new TravelProfile();
            entityManager.persist(travelProfile);

            Employee e1 = new Employee();
            e1.setProfile(travelProfile);
            entityManager.persist(e1);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
