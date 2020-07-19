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

    @OneToMany(mappedBy = "marks")
    private Set<Model> modelList;

    public Mark() {
    }

    public Mark(String markName, Set<Model> modelList) {
        this.name = markName;
        this.modelList = modelList;
    }

    public Mark(String markName) {
        this.name = markName;
    }
}
