package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Colour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends CrudRepository<Colour, Long> {
    Colour getByName(String name);

    Colour getById(Long id);

}
