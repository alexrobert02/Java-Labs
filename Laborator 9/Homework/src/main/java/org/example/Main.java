package org.example;

import items.Genre;
import items.Album;
import items.Artist;
import repositories.AlbumRepository;
import repositories.ArtistRepository;
import repositories.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create entity manager factory and entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeworkPU");
        EntityManager em = emf.createEntityManager();

        // Create repositories
        ArtistRepository artistRepository = new ArtistRepository(emf);
        GenreRepository genreRepository = new GenreRepository(emf);
        AlbumRepository albumRepository = new AlbumRepository(emf);

        // Create artists
        Artist Oscar = new Artist("Oscar");
        Artist Ian = new Artist("Ian");
        Artist Calinacho = new Artist("Calinacho");

        // Create genres
        Genre Trap = new Genre("Trap");
        Genre RNB = new Genre("RNB");

        // Create albums
        Album album1 = new Album(2023, "Inapoi la Viata", Oscar, Trap);
        Album album2 = new Album(2022, "Voodoo", Ian, Trap );
        Album album3 = new Album(2010, "Intr-o buna zi totul va fi bine", Calinacho, RNB);

        albumRepository.create(album1);
        albumRepository.create(album2);
        albumRepository.create(album3);

        Album foundAlbum1 = albumRepository.findById(1);
        System.out.println(foundAlbum1.getTitle());

        Album foundAlbum2 = albumRepository.findByName("Voodoo");
        System.out.println(foundAlbum2.getArtist());

        List<Album> allAlbums = albumRepository.findAll();
        for (Album album : allAlbums) {
            System.out.println(album.getTitle());
        }

        em.close();
        emf.close();
    }
}
