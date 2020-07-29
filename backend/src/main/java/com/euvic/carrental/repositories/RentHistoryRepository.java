package com.euvic.carrental.repositories;

import com.euvic.carrental.model.RentHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentHistoryRepository extends CrudRepository<RentHistory, Long> {
}
