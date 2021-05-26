package entity;

import TrainModel.TrainMatchedModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            var ntlist = entityManager.createQuery("from TrainsEntity where name=:nm", TrainsEntity.class).setParameter("nm", name).getResultList();
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

    public static List<TrainsonstationsEntity> searchConnections(String s1, String s2, int h) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            var st1ID = entityManager.createQuery("from StationsEntity where name=:nm", StationsEntity.class).setParameter("nm", s1).getSingleResult().getId();
            var st2ID = entityManager.createQuery("from StationsEntity where name=:nm", StationsEntity.class).setParameter("nm", s2).getSingleResult().getId();
            List<TrainsonstationsEntity> cns;
            if (h == -1) {
                cns = entityManager.createQuery("from TrainsonstationsEntity where stationID=:nm", TrainsonstationsEntity.class).setParameter("nm", s2).getResultList();
            } else {
                cns = entityManager.createQuery("from TrainsonstationsEntity where stationID=:nm and stime=:nm2 ", TrainsonstationsEntity.class).setParameter("nm", s2).setParameter("nm2", h).getResultList();
            }

            entityManager.getTransaction().commit();

            List<TrainsonstationsEntity> connections = new ArrayList<TrainsonstationsEntity>();

            for (var c : cns) {
                entityManager.getTransaction().begin();
                var tmp = entityManager.createQuery("from TrainsonstationsEntity where trainID=:nm and stationID=:nm2", TrainsonstationsEntity.class).setParameter("nm", c.getTrainId()).setParameter("nm2", st2ID).getResultList();
                entityManager.getTransaction().commit();
                connections.addAll(tmp);
            }

            return connections;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static TrainsEntity getTrainByID(int ID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            TrainsEntity t = entityManager.createQuery("from TrainsEntity where ID=:id", TrainsEntity.class).setParameter("id", ID).getSingleResult();
            entityManager.getTransaction().commit();
            return t;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static TrainsEntity getTrainByName(String nm) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            var t = entityManager.createQuery("from TrainsEntity where name=:nm", TrainsEntity.class).setParameter("nm", nm).getResultList();
            entityManager.getTransaction().commit();
            return t.get(0);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static List<TrainMatchedModel> getTickets() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            entityManager.getTransaction().begin();
            var te = entityManager.createQuery("from TicketsEntity", TicketsEntity.class).getResultList();
            entityManager.getTransaction().commit();

            List<TrainMatchedModel> res = new ArrayList<TrainMatchedModel>();

            for (var t : te) {
                var tr = EntityUtil.getTrainByID(t.getTrainId());
                res.add(new TrainMatchedModel(tr.getName(), tr.getTraveltime(), tr.getTraveltime() - 3, tr.getTicketCost(), t.getId()));
            }

            return res;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void cancelTicketByID(int ID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            TicketsEntity ticket = entityManager.find(TicketsEntity.class, ID); //find by pk

            entityManager.getTransaction().begin();
            entityManager.remove(ticket);
            entityManager.getTransaction().commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static double getRateMean(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

//            Criteria cr = session.createCriteria(Employee.class);
//            List results = cr.list();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RatingEntity> criteriaQuery = criteriaBuilder.createQuery(RatingEntity.class);
            Root<RatingEntity> itemRoot = criteriaQuery.from(RatingEntity.class);

            Predicate predicate = criteriaBuilder.equal(itemRoot.get("stationid"), id);

            criteriaQuery.where(predicate);
            List<RatingEntity> items = entityManager.createQuery(criteriaQuery).getResultList();

            double mean=0;
            int i = 0;

            for (RatingEntity re: items) {
                mean+=re.getRate();
                i++;
            }
            if(i==0){
                return 0;
            }

            return mean/i;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
