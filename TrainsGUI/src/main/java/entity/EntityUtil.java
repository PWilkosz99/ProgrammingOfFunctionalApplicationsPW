package entity;

import TrainModel.Train;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EntityUtil {

    public static <T> void addToDB(T obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static <T> void deleteFromDB(T obj) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(obj));
            entityManager.getTransaction().commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void updateCapacity(StationsEntity s, int n) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(s).setCapacity(n);
            entityManager.getTransaction().commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static List<StationsEntity> loadStationsDB() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<StationsEntity> se = entityManager.createQuery("from StationsEntity", StationsEntity.class).getResultList();
            entityManager.getTransaction().commit();
            return se;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static List<TrainsEntity> loadTrainsDB(int n) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<TrainsonstationsEntity> tose = entityManager.createQuery("from TrainsonstationsEntity where stationID=:n", TrainsonstationsEntity.class).setParameter("n", n).getResultList();
            entityManager.getTransaction().commit();

            if (tose.size() > 0) {
                List<TrainsEntity> res = new ArrayList<TrainsEntity>();
                for (var a : tose) {
                    entityManager.getTransaction().begin();
                    List<TrainsEntity> te = entityManager.createQuery("from TrainsEntity where ID=:n", TrainsEntity.class).setParameter("n", a.getTrainId()).getResultList();
                    entityManager.getTransaction().commit();
                    res.addAll(te);
                }
                return res;
            } else {
                return null;
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void deleteTrainByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            var tr = entityManager.createQuery("from TrainsEntity where name=:nm", TrainsEntity.class).setParameter("nm", name).getSingleResult();
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            var ntlist = entityManager.createQuery("from TrainsEntity where name=:nm", TrainsEntity.class).setParameter("nm", name).getResultList();
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            for (var r : ntlist) {
                entityManager.remove(r);
            }
            entityManager.getTransaction().commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
