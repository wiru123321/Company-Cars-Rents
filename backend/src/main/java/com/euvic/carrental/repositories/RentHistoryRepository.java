package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.RentHistory;
import com.euvic.carrental.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentHistoryRepository extends CrudRepository<RentHistory, Long> {
    RentHistory findByCarAndDateFrom(Car car, LocalDateTime dateFrom);

    List<RentHistory> findAllByUser(User user);
}
