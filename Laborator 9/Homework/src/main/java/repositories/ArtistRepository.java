package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import items.Artist;

public class ArtistRepository extends AbstractRepository<Artist> {

    public ArtistRepository(EntityManagerFactory emf) {
        super(emf, Artist.class);
    }

    @Override
    public Artist findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM Artist a WHERE a.name = :name");
            query.setParameter("name", name);
            return (Artist) query.getSingleResult();
        } finally {
            em.close();
        }
    }
}

