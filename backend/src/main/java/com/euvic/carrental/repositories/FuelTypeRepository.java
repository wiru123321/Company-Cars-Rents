package com.euvic.carrental.repositories;

import com.euvic.carrental.model.FuelType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    FuelType findByName(String name);
}
