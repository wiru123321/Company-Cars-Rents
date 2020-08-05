package com.euvic.carrental.repositories;

import com.euvic.carrental.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByLogin(String login);
    List<User>  findAllByIsActive(Boolean isActive);
}
