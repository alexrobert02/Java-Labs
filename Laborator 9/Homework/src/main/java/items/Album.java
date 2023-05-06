package items;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "albums")
public class Album implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "title")
    private String title;

    // Define many-to-one relationship with Artist entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // Define many-to-many relationship with Genre entity
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "album_genre", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Album() {
    }

    public Album(int releaseYear, String title, Artist artist, Genre genre) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", releaseYear=" + releaseYear + ", title='" + title + '\'' + ", artist=" + artist + ", genres=" + genres + '}';
    }
}

