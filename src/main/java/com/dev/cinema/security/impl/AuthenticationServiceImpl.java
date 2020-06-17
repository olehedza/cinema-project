package com.dev.cinema.security.impl;

import com.dev.cinema.error.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService cartService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);

        if (userFromDB != null
                && userFromDB.getPassword()
                .equals(passwordEncoder.encode(password))) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        if (userService.findByEmail(email) != null) {
            throw new AuthenticationException(String
                    .format("User with email '%s' already exists", email));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        User userFromDB = userService.add(user);
        cartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }

}
