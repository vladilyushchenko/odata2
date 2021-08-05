package com.leverx.odata.repository;

import com.leverx.odata.entity.jpa.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    void delete(User user);

    List<User> findAll();

    User findById(Long id);
}
