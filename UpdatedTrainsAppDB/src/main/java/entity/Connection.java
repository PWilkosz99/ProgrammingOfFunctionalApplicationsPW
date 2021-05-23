package entity;

import javax.persistence.*;

public class Connection {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            Integer ID = null;
            transaction.begin();

            TrainsEntity tr = new TrainsEntity(2,3,4, 5);

            entityManager.persist(tr);

            transaction.commit();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
