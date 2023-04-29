package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DAO<Genre> {

    // Create method to insert new genre
    @Override
    public void create(Genre genre) throws SQLException {
        Connection con = Database.getConnection();

        // Check if genre already exists in database
        PreparedStatement check = con.prepareStatement("select * from genres where name = ((?))");
        check.setString(1, genre.getName());
        ResultSet resultSet = check.executeQuery();

        // If genre doesn't exist, insert them into database
        if (!resultSet.next()) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into genres (name) values (?)")) {
                pstmt.setString(1, genre.getName());
                pstmt.executeUpdate();
            }
        }
        con.close();
    }

    // Find genre by name
    @Override
    public Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from genres where name='" + name + "'")) {
            if (rs.next()) {
                Genre genre = new Genre(rs.getInt(1), rs.getString(2));
                con.close();
                return genre;
            } else {
                con.close();
                return null;
            }
        }
    }

    // Find genre by id
    @Override
    public Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from genres where id='" + id + "'")) {
            if (rs.next()) {
                Genre genre = new Genre(rs.getInt(1), rs.getString(2));
                con.close();
                return genre;
            } else {
                con.close();
                return null;
            }
        }
    }

    // Find all genres
    @Override
    public List<Genre> findAll() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from genres")) {
            while (rs.next()) {
                genres.add(new Genre(rs.getInt(1), rs.getString(2)));
            }
        }
        con.close();
        return genres;
    }

}
