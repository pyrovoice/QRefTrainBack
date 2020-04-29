package com.quidditchreftraining.qreftrain.dao;

import com.quidditchreftraining.qreftrain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    Optional<User> findBydisplayName(String username);
    Optional<User> findByEmail(String email);
}
