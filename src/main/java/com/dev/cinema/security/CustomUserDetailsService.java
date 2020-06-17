package com.dev.cinema.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = findUserByEmail(email);
        UserBuilder userBuilder = null;

        if (user != null) {
            userBuilder = withUsername(email);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRoles().stream()
                    .map(r -> r.getName().toString())
                    .collect(Collectors.toList())
                    .toArray(String[]::new));
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException("No user found! Check input.");
        }

    }

    private User findUserByEmail(String email) {
        return this.userService.findByEmail(email);
    }
}
