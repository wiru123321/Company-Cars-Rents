package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
