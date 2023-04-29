package org.example;

import com.opencsv.CSVReader;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CSVTool {
    private AlbumDAO albumDAO = new AlbumDAO();
    private GenreDAO genreDAO = new GenreDAO();
    private ArtistDAO artistDAO = new ArtistDAO();

    public void execute() throws SQLException {
        System.out.println("Se incarca basa de date.");
        CSVReader reader = null; // Initialize CSVReader object
        try {
            reader = new CSVReader(new FileReader("albumlist.csv")); // Read CSV file
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[3].contains("'")) { // Replace single quotes with backticks to prevent SQL injection
                    StringBuilder stringBuilder = new StringBuilder(nextLine[3]);
                    stringBuilder.replace(stringBuilder.indexOf("'"), stringBuilder.indexOf("'") + 1, "`");
                    nextLine[3] = stringBuilder.toString();
                }
                // Create Album object from data in the row
                Album album = new Album(Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), nextLine[2], nextLine[3], nextLine[4]);

                // If Artist does not exist, create new Artist object and add to database
                if (artistDAO.findByName(album.getArtist_name()) == null) {
                    Artist artist = new Artist(1, album.getArtist_name());
                    artistDAO.create(artist);
                }

                // If Genre does not exist, create new Genre object(s) and add to database
                if (genreDAO.findByName(album.getGenres_name()) == null) {
                    String[] genresList = album.getGenres_name().split(", ");
                    for (int i = 0; i < genresList.length; i++) {
                        Genre genre = new Genre(1, genresList[i]);
                        genreDAO.create(genre);
                    }
                }

                // Add Album object to database
                albumDAO.create(album);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
