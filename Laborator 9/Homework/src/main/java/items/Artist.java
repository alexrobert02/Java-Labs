package items;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "artists")
public class Artist implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    // Specifies a one-to-many relationship between this entity and the Album entity
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "items.Artist{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}