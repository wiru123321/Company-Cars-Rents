package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    Rent findByCarAndDateFrom(Car car, LocalDateTime dateFrom);

    List<Rent> findAllByIsActive(Boolean isActive);

    List<Rent> findAllByUser(User user);

    List<Rent> findAllByUserAndIsActive(User user, boolean isActive);

    List<Rent> findAllByCar(Car car);
}
