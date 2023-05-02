package items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "artists")
@NamedQueries({
        // Define named queries
        @NamedQuery(name = "items.Artist.findAll", query = "select e from Artist e order by e.name"),

        @NamedQuery(name = "items.Artist.findByName", query = "select e from Artist e where e.name like :name"),

        @NamedQuery(name = "items.Artist.findById", query = "select e from Artist e where e.id = :id"),

        @NamedQuery(name = "items.Artist.getCount", query = "select e from Artist e")
})


public class Artist implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;

    public Artist() {
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "items.Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
