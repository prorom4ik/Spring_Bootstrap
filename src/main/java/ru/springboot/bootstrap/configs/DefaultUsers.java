package ru.springboot.bootstrap.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.springboot.bootstrap.model.Role;
import ru.springboot.bootstrap.model.User;
import ru.springboot.bootstrap.service.RoleService;
import ru.springboot.bootstrap.service.RoleServiceImpl;
import ru.springboot.bootstrap.service.UserService;
import ru.springboot.bootstrap.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DefaultUsers {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("spring.jpa.hibernate.ddl-auto")
    String ddl_auto;

    public DefaultUsers(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (ddl_auto.equals("create") || ddl_auto.equals("create-drop")) {
            Role roleAdmin = new Role(1L,"ROLE_ADMIN");
            Role roleUser = new Role(2L,"ROLE_USER");

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);

            User romanUser = new User(1L,"roman","torop",19,"admin@mail.ru", passwordEncoder.encode("123"));
            romanUser.setRoles(new HashSet(Arrays.asList(roleAdmin,roleUser)));

            User makarUser  = new User(2L,"makar","pavlov",21, "user@mail.ru", passwordEncoder.encode("1234"));
            makarUser.setRoles(new HashSet(List.of(roleUser)));

            userService.saveUser(romanUser);
            userService.saveUser(makarUser);
        }
    }
}