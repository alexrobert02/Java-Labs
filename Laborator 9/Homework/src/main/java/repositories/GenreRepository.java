package repositories;

import items.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class GenreRepository extends AbstractRepository<Genre> {

    public GenreRepository(EntityManagerFactory emf) {
        super(emf, Genre.class);
    }

    @Override
    public Genre findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT g FROM Genre g WHERE g.name = :name");
            query.setParameter("name", name);
            return (Genre) query.getSingleResult();
        } finally {
            em.close();
        }
    }
}

