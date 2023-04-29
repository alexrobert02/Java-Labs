package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO implements DAO<Artist> {

    // Create method to insert new artist
    @Override
    public void create(Artist artist) throws SQLException {
        Connection con = Database.getConnection();

        // Check if artist already exists in database
        PreparedStatement check = con.prepareStatement("select * from artists where name = ((?))");
        check.setString(1, artist.getName());
        ResultSet resultSet = check.executeQuery();

        // If artist doesn't exist, insert them into database
        if (!resultSet.next()) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into artists (name) values (?)")) {
                pstmt.setString(1, artist.getName());
                pstmt.executeUpdate();
            }
        }
        con.close();
    }

    // Find artist by name
    @Override
    public Artist findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from artists where name='" + name + "'")) {
            if (rs.next()) {
                Artist artist = new Artist(rs.getInt(1), rs.getString(2));
                con.close();
                return artist;
            } else {
                con.close();
                return null;
            }
        }
    }

    // Find artist by ID
    @Override
    public Artist findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from artists where id='" + id + "'")) {
            if (rs.next()) {
                Artist artist = new Artist(rs.getInt(1), rs.getString(2));
                con.close();
                return artist;
            } else {
                con.close();
                return null;
            }
        }
    }

    // Find all artists
    public List<Artist> findAll() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from artists")) {
            while (rs.next()) {
                artists.add(new Artist(rs.getInt(1), rs.getString(2)));
            }
        }
        con.close();
        return artists;
    }
}
