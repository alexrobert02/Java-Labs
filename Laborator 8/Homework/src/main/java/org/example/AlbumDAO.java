package org.example;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO implements DAO<Album> {

    // Create method to insert new album
    @Override
    public void create(Album album) throws SQLException {
        Connection con = Database.getConnection();
        int idArtist = new ArtistDAO().findByName(album.getArtist_name()).getId();

        // Split genres name into a string array
        String[] genresVec = album.getGenres_name().split(", ");
        for (String g : genresVec) {
            int idGenre = new GenreDAO().findByName(g).getId();

            // Check if album already exists
            PreparedStatement check = con.prepareStatement("select * from albums where release_year = (?) and title = (?) and artist_id = (?) and genre_id = (?)");
            check.setInt(1, album.getRelease_year());
            check.setString(2, album.getTitle());
            check.setInt(3, idArtist);
            check.setInt(4, idGenre);
            ResultSet resultSet = check.executeQuery();
            if (!resultSet.next()) {

                // Insert album if it doesn't already exist
                try (PreparedStatement pstmt = con.prepareStatement("insert into albums (release_year,title,artist_id, genre_id) values (?,?,?,?)")) {
                    pstmt.setInt(1, album.getRelease_year());
                    pstmt.setString(2, album.getTitle());
                    pstmt.setInt(3, idArtist);
                    pstmt.setInt(4, idGenre);
                    pstmt.executeUpdate();
                }
            }

        }

        int idAlbum = new AlbumDAO().findByName(album.getTitle()).getId();

        String[] genresVector = album.getGenres_name().split(", ");
        for (String g : genresVector) {
            int idGenres = new GenreDAO().findByName(g).getId();

            // Insert album-genre mapping
            try (PreparedStatement pstmt = con.prepareStatement("insert into album_genre (album_id,genre_id) values (?,?)")) {
                pstmt.setInt(1, idAlbum);
                pstmt.setInt(2, idGenres);
                pstmt.executeUpdate();
            }
        }
        con.close();
    }

    // Find album by name
    @Override
    public Album findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "select al.id, al.release_year, al.title, ar.name, gr.name from albums al join artists ar on al.artist_id = ar.id join genres gr on gr.id = al.genre_id where al.title = (?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    con.close();
                    return null;
                }
                Album album = new Album();
                album.setId(rs.getInt(1));
                album.setRelease_year(rs.getInt(2));
                album.setTitle(rs.getString(3));
                album.setArtist_name(rs.getString(4));
                StringBuilder genre_name = new StringBuilder();
                genre_name.append(rs.getString(5));
                while (rs.next()) genre_name.append(", " + rs.getString(3));
                album.setGenres_name(genre_name.toString());
                con.close();
                return album;
            }
        }
    }

    // Find genre by id
    @Override
    public Album findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select al.id, al.release_year, al.title, ar.name, gr.name from albums al join artists ar on al.artist_id = ar.id join genres gr on gr.id = al.genre_id where id='" + id + "'")) {
            if (!rs.next()) {
                con.close();
                return null;
            }
            Album album = new Album();
            album.setId(rs.getInt(1));
            album.setRelease_year(rs.getInt(2));
            album.setTitle(rs.getString(3));
            album.setArtist_name(rs.getString(4));
            StringBuilder genre_name = new StringBuilder();
            genre_name.append(rs.getString(3));
            while (rs.next()) genre_name.append(", " + rs.getString(3));
            album.setGenres_name(genre_name.toString());
            con.close();
            return album;
        }
    }

    // Find all albums
    @Override
    public List<Album> findAll() throws SQLException {
        List<Album> albums = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select al.id, al.release_year, al.title, ar.name, gr.name from albums al join artists ar on al.artist_id = ar.id join genres gr on gr.id = al.genre_id")) {
            while (rs.next()) {
                albums.add(new Album(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        con.close();
        return albums;
    }

    // Print all albums
    public void printAlbums() throws SQLException {
        List<Album> albums = findAll();
        for (Album album : albums) {
            System.out.println("ID: " + album.getId());
            System.out.println("Title: " + album.getTitle());
            System.out.println("Artist: " + album.getArtist_name());
            System.out.println("Release Year: " + album.getRelease_year());
            System.out.println("Genres: " + album.getGenres_name());
            System.out.println("------------------------");
        }
    }
}
