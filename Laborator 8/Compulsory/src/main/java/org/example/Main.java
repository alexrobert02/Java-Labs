package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");

            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();

            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");

            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();


            albums.Print();

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);

            Database.rollback();
        }
    }
}