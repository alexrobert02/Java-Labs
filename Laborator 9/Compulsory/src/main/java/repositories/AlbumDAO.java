package repositories;

import items.Album;
import org.example.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class AlbumDAO implements DAO<Album> {

    private EntityManagerFactory emf;

    public AlbumDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AlbumDAO() {
    }

    @Override
    public void create(Album album) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(album);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Album findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Album findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Album.class, name);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Album> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM Album a");
            return query.getResultList();
        } finally {
            em.close();
        }
    }


}
