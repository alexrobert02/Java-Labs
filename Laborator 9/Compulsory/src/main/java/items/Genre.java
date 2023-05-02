package items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "genres")
@NamedQueries({
        // Define named queries
        @NamedQuery(name = "items.Genre.findAll", query = "select e from Genre e order by e.name"),

        @NamedQuery(name = "items.Genre.findByName", query = "select e from Genre e where e.name like :name"),

        @NamedQuery(name = "items.Genre.findById", query = "select e from Genre e where e.id = :id"),

        @NamedQuery(name = "items.Genre.getCount", query = "select e from Genre e")})


public class Genre implements Serializable {
    // Define the ID field as the primary key with an automatically generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "items.Genre{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
