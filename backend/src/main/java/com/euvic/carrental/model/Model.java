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
    private String modelName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mark mark;

    public Model() {
    }

    public Model(String modelName) {
        this.modelName = modelName;
    }
}
