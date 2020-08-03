package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {
    Model findByName(String name);
    List<Model> findAllByName(String name);
}
