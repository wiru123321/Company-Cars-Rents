package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
    Mark findByName(String name);
}
