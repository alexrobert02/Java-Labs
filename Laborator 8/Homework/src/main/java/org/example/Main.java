package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            CSVTool csvTool = new CSVTool();
            csvTool.execute();

            AlbumDAO albumDAO = new AlbumDAO();
            albumDAO.printAlbums();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
