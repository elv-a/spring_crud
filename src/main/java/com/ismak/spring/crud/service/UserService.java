package com.ismak.spring.crud.service;

import com.ismak.spring.crud.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User show(int id);

    void save(User user);

    void update(int id, User updateUser);

    void delete(int id);
}
