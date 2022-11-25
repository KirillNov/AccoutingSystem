package com.novikovkirill.test.accountingsystem.services;

import com.novikovkirill.test.accountingsystem.models.User;

import java.util.List;

public interface UserService {

    boolean createUser(User user);

    User findByEmail(String email);

    List<User> findAll();

    void delete(Long id);

}
