package ru.springboot.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springboot.bootstrap.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    boolean existsById(Long id);
}
