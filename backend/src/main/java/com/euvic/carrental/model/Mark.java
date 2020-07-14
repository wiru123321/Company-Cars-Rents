package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "marks")
@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Mark() {
    }

    public Mark(String markName) {
        this.name = markName;
    }
}
