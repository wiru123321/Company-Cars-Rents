package com.euvic.carrental.repositories;

import com.euvic.carrental.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
