package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "marks")
@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    //TODO check one to many (must be)
    @OneToMany()
    private List<Model> modelList;

    public Mark() {
    }

    public Mark(String markName,List<Model> modelList) {
        this.name = markName;
        this.modelList = modelList;
    }
}
