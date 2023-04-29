package org.example;

public class Album {
    private int id;
    private int release_year;
    private String title;
    private String artist_name;
    private String genres_name;


    public Album() {
    }

    public Album(int id, int release_year, String title, String artist_name, String genres_name) {
        this.id = id;
        this.release_year = release_year;
        this.title = title;
        this.artist_name = artist_name;
        this.genres_name = genres_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGenres_name() {
        return genres_name;
    }

    public void setGenres_name(String genre_name) {
        this.genres_name = genre_name;
    }
}
