package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "car_types")
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "type",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Car> cars;

    public Type() {
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }
}
