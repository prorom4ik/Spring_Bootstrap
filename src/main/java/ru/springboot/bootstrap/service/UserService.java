package ru.springboot.bootstrap.service;

import ru.springboot.bootstrap.model.User;

import java.util.List;

public interface UserService {
    User findByEmail (String email);
    User findById(Long id);
    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);
    void updateUser(Long id, User updatedUser);
}
