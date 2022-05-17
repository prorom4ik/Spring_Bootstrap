package ru.springboot.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.springboot.bootstrap.model.User;
import ru.springboot.bootstrap.service.RoleService;
import ru.springboot.bootstrap.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String findAll(ModelMap model, Principal principal) {
        List<User> users = userService.findAll();
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("current", user);
        return "users";
    }

    @PostMapping
    public String createUser(User user) {
        String password  = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, Principal principal) {
        User current = userService.findByEmail(principal.getName());
        Authentication authentication = new UsernamePasswordAuthenticationToken(current, current.getPassword(), current.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String password  = user.getPassword();
        if (password != null && !password.equals("")) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userService.updateUser(id, user);
        return "redirect:/admin";

    }
}
