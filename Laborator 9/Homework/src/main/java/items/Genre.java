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
@Table(name = "genres")
public class Genre implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    // Specifies a one-to-many relationship between this entity and the Album entity
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Album> albums = new HashSet<>();

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "items.Genre{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
