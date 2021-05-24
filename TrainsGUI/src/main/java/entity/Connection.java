package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Connection {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            Integer ID = null;


            //ADD
            //transaction.begin();
            //TrainsEntity tr = new TrainsEntity(90,1,2, TrainState.Cancelled);
            //entityManager.persist(tr);
            //transaction.commit();

            //SELECT
            entityManager.getTransaction().begin();
            List<TrainsEntity> result = entityManager.createQuery("from TrainsEntity \n", TrainsEntity.class ).getResultList();
            for ( TrainsEntity event : result ) {
                System.out.println( "Event (" + event.getId() + ") : " + event.getState() );
            }
            entityManager.getTransaction().commit();
            entityManager.close();

            //REMOVE
            //TrainsEntity employee = entityManager.find(TrainsEntity.class, 5); //find by pk

            //entityManager.getTransaction().begin();
            //entityManager.remove(employee);
            //entityManager.getTransaction().commit();

            //UPDATE
            //TrainsEntity employee = entityManager.find(TrainsEntity.class, 2);

            //entityManager.getTransaction().begin();
            //employee.setCars(200);
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
