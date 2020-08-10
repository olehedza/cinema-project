package com.dev.cinema.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/inject-data", "/login", "/register")
                    .permitAll()
                .antMatchers(HttpMethod.POST,"/shopping-carts/**", "/orders/complete")
                    .hasRole("USER")
                .antMatchers(HttpMethod.POST, "/cinema-halls", "/movies",
                        "/movie-sessions")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/by-email", "/hello", "/movies",
                        "/cinema-halls", "/shopping-carts", "/movie-sessions/available", "/orders")
                    .hasAnyRole("USER", "ADMIN")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .permitAll()
                .and()
                    .httpBasic()
                .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and()
                    .csrf()
                    .disable();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
