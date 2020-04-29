package com.quidditchreftraining.qreftrain.service;


import com.quidditchreftraining.qreftrain.model.User;
import com.quidditchreftraining.qreftrain.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);
}
