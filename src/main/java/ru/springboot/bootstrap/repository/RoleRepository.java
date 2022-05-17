package ru.springboot.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springboot.bootstrap.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //boolean exists(Role role);
}
