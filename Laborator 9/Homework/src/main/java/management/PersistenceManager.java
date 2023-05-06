package management;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static EntityManagerFactory emf = null;
    private static PersistenceManager singleInstance = null;

    private PersistenceManager() {
        // Create an EntityManagerFactory object with the "default" persistence unit
        PersistenceManager.emf = Persistence.createEntityManagerFactory("HomeworkPU");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (singleInstance == null) {
            singleInstance = new PersistenceManager();
        }
        return PersistenceManager.emf;
    }

    public static void closeEntityManagerFactory() {
        PersistenceManager.emf.close();
    }
}
