package com.ismak.spring.crud.dao;

import com.ismak.spring.crud.model.User;

import java.util.List;

public interface UserDAO {
    List<User> index();

    User show(int id);

    void save(User user);

    void update(int id, User updateUser);

    void delete(int id);
}
