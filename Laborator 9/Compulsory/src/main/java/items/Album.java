package items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "albums")
@NamedQueries({
        // Define named queries
        @NamedQuery(name = "items.Album.findAll", query = "select e from Album e order by e.title"),

        @NamedQuery(name = "items.Album.findByTitle", query = "select e from Album e where e.title = :title"),

        @NamedQuery(name = "items.Album.findById", query = "select e from Album e where e.id = :id"),

        @NamedQuery(name = "items.Album.getCount", query = "select e from Album e")
})


public class Album implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "release_year")
    private int release_year;

    @Column(name = "title")
    private String title;

    @Column
    private String artist_name;

    @Column
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
}
