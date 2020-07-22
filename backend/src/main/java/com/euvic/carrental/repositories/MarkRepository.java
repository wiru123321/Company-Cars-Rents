package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Mark;
import org.springframework.data.repository.CrudRepository;

public interface MarkRepository extends CrudRepository<Mark, Long> {
    Mark findByName(String name);
}
