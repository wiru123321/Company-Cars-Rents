package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "models")
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Mark mark;

    public Model() {
    }

    public Model(Long id, String name, Mark mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }
}
