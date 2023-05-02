package org.example;

import items.Album;
import items.Artist;
import items.Genre;
import repositories.AlbumDAO;
import repositories.ArtistDAO;
import repositories.GenreDAO;
import management.PersistenceManager;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = PersistenceManager.getEntityManagerFactory();

        ArtistDAO artistDAO = new ArtistDAO(emf);
        GenreDAO genreDao = new GenreDAO(emf);
        AlbumDAO albumDAO = new AlbumDAO(emf);

        Artist artist1 = new Artist(1, "Oscar");
        Artist artist2 = new Artist(2, "Ian");
        Artist artist3 = new Artist(3, "Calinacho");

        Genre genre1 = new Genre(1, "Trap");
        Genre genre2 = new Genre(2, "RNB");

        Album album1 = new Album(1, 2023, "Inapoi la Viata", "Oscar", "Trap");
        Album album2 = new Album(2, 2022, "Vodoo", "Ian", "Trap");
        Album album3 = new Album(3, 2010, "Intr-o buna zi totul va fi bine", "Calinacho", "RNB");

        albumDAO.create(album1);
        albumDAO.create(album2);
        albumDAO.create(album3);

        Album foundAlbum1 = albumDAO.findById(1);
        System.out.println(foundAlbum1.getTitle());

        Album foundAlbum2 = albumDAO.findByName("Album 2");
        System.out.println(foundAlbum2.getArtist_name());

        List<Album> allAlbums = albumDAO.findAll();
        for (Album album : allAlbums) {
            System.out.println(album.getTitle());
        }

        PersistenceManager.closeEntityManagerFactory();
    }
}
