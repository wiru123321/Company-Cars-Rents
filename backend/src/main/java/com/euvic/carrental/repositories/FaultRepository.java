package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaultRepository extends CrudRepository<Fault, Long> {
    List<Fault> findByCar(Car car);
}
