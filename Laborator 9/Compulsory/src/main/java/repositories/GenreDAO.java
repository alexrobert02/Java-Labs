package repositories;

import items.Genre;
import org.example.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;

public class GenreDAO implements DAO<Genre> {
    private EntityManagerFactory emf;

    public GenreDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Genre genre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(genre);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Genre findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Genre.class, id);
        } catch (EntityNotFoundException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Genre findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Genre.class, name);
        } catch (EntityNotFoundException e) {
            return null;
        } finally {
            em.close();
        }
    }


    @Override
    public List<Genre> findAll() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("items.Genre.findAll", Genre.class).getResultList();
    }
}