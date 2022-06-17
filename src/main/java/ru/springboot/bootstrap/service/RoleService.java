package ru.springboot.bootstrap.service;

import ru.springboot.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    void addRole(Role role);
}
