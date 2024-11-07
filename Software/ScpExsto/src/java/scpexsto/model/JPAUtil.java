package scpexsto.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static JPAUtil instance;
    private EntityManagerFactory emf;

    private JPAUtil() {
        emf = Persistence.createEntityManagerFactory("scpteste");
    }

    public static JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}