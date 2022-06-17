package ru.springboot.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springboot.bootstrap.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
