package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Long> {
    Model findByName(String name);
}
