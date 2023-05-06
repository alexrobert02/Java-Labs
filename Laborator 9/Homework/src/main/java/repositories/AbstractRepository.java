package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public abstract class AbstractRepository<T> {
    protected final EntityManagerFactory emf;
    private final Class<T> entityType;

    public AbstractRepository(EntityManagerFactory emf, Class<T> entityType) {
        this.emf = emf;
        this.entityType = entityType;
    }

    public void create(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public T findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityType, id);
        } catch (EntityNotFoundException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e");
        List<T> entities = query.getResultList();
        em.close();
        return entities;
    }

    public abstract T findByName(String name);
}

