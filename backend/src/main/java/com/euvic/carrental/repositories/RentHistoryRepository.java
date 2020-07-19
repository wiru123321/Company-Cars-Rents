package com.euvic.carrental.repositories;

import com.euvic.carrental.model.RentHistory;
import org.springframework.data.repository.CrudRepository;

public interface RentHistoryRepository extends CrudRepository<RentHistory, Long> {
}
