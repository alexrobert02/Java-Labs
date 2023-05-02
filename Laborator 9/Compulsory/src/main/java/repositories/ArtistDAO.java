package repositories;

import items.Artist;
import org.example.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class ArtistDAO implements DAO<Artist> {
    private EntityManagerFactory emf;

    public ArtistDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Artist artist) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(artist);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Artist findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Artist.class, id);
        } catch (EntityNotFoundException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Artist> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Artist a");
        List<Artist> artists = query.getResultList();
        em.close();
        return artists;
    }

    @Override
    public Artist findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Artist.class, name);
        } catch (EntityNotFoundException e) {
            return null;
        } finally {
            em.close();
        }
    }
}