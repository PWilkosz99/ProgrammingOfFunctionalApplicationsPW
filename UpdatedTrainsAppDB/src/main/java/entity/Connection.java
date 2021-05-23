package entity;

import javax.persistence.*;
import java.util.List;

public class Connection {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            Integer ID = null;
            //transaction.begin();

            //ADD
            //TrainsEntity tr = new TrainsEntity(2,3,4, 5);
            //entityManager.persist(tr);
            //transaction.commit();

            //SELECT
            //entityManager = entityManagerFactory.createEntityManager();
            //entityManager.getTransaction().begin();
            //List<TrainsEntity> result = entityManager.createQuery( "from TrainsEntity ", TrainsEntity.class ).getResultList();
            //for ( TrainsEntity event : result ) {
            //    System.out.println( "Event (" + event.getId() + ") : " + event.getCars() );
            //}
            //entityManager.getTransaction().commit();
            //entityManager.close();

            //REMOVE
            //TrainsEntity employee = entityManager.find(TrainsEntity.class, 5); //find by pk

            //entityManager.getTransaction().begin();
            //entityManager.remove(employee);
            //entityManager.getTransaction().commit();


        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
