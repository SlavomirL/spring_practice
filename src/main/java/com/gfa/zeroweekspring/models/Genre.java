package com.gfa.zeroweekspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Genre {

    @Id
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Movie> movies;
}