package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InjectDataController {
    private static final String MESSAGE = "Test users with roles were added to DB.";
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/inject-data")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);

        User adminUser = new User();
        adminUser.setEmail("pythonadmin@google.com");
        adminUser.setPassword(passwordEncoder.encode("meshuggah"));
        adminUser.setRoles(Set.of(adminRole));
        userService.add(adminUser);
        shoppingCartService.registerNewShoppingCart(adminUser);
        User normalUser = new User();
        normalUser.setEmail("userjava@yahoo.com");
        normalUser.setPassword(passwordEncoder.encode("lofofora"));
        normalUser.setRoles(Set.of(userRole));
        userService.add(normalUser);
        shoppingCartService.registerNewShoppingCart(normalUser);
        return MESSAGE;
    }
}

