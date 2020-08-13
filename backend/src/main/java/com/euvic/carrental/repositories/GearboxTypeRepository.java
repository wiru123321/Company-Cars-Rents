package com.euvic.carrental.repositories;

import com.euvic.carrental.model.GearboxType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearboxTypeRepository extends CrudRepository<GearboxType, Long> {
    GearboxType findByName(String name);
}
