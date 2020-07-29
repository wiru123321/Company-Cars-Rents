package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    Rent findByCarAndDateFrom(Car car, Date date);
}
