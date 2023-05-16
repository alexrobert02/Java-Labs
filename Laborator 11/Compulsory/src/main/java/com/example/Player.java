package com.example;

import jakarta.persistence.*;

@Entity
@Table(name="player")
public class Player {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Player(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}