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

    //TODO add to constructor Set ? or maybe by setter
    public Mark(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
