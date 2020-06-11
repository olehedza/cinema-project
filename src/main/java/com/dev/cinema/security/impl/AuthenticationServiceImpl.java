package com.dev.cinema.security.impl;

import com.dev.cinema.error.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final HashUtil hashUtil;
    private final UserService userService;
    private final ShoppingCartService cartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);

        if (userFromDB != null
                && hashUtil.getPasswordDigest(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
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
        user.setSalt(hashUtil.getSalt());
        user.setPassword(hashUtil.getPasswordDigest(password, user.getSalt()));
        User userFromDB = userService.add(user);
        cartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
