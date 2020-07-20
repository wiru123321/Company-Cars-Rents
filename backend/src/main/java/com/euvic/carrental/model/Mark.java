package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "marks")
@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "mark")
    private Set<Model> modelList;

    public Mark(final String name) {
        this.name = name;
    }
}
