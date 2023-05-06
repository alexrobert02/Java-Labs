package repositories;

import javax.persistence.EntityManagerFactory;
import items.Album;

public class AlbumRepository extends AbstractRepository<Album> {

    public AlbumRepository(EntityManagerFactory emf) {
        super(emf, Album.class);
    }

    @Override
    public Album findByName(String name) {
        throw new UnsupportedOperationException("AlbumRepository does not support findByName");
    }
}

