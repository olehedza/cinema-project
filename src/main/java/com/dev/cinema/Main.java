package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final UserService userService =
            (UserService) injector.getInstance(UserService.class);

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        movie1.setTitle("Filth");
        Movie movie2 = new Movie();
        movie2.setTitle("Once upon a time in Hollywood");
        Movie movie3 = new Movie();
        movie3.setTitle("Joker");
        Movie movie4 = new Movie();
        movie4.setTitle("The Gentlemen");
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.add(movie3);
        movieService.add(movie4);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHallOne = new CinemaHall();
        cinemaHallOne.setCapacity(200);
        cinemaHallOne.setDescription("first hall");
        cinemaHallOne = cinemaHallService.add(cinemaHallOne);
        CinemaHall cinemaHallTwo = new CinemaHall();
        cinemaHallTwo.setCapacity(80);
        cinemaHallTwo.setDescription("second hall");
        cinemaHallTwo = cinemaHallService.add(cinemaHallTwo);
        CinemaHall cinemaHallThree = new CinemaHall();
        cinemaHallThree.setCapacity(140);
        cinemaHallThree.setDescription("third hall");
        cinemaHallThree = cinemaHallService.add(cinemaHallThree);

        MovieSession movieSessionOne = new MovieSession();
        movieSessionOne.setCinemaHall(cinemaHallOne);
        movieSessionOne.setMovie(movie1);
        movieSessionOne.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 10, 12),
                        LocalTime.of(20, 30)));
        movieSessionService.add(movieSessionOne);
        MovieSession movieSessionTwo = new MovieSession();
        movieSessionTwo.setCinemaHall(cinemaHallTwo);
        movieSessionTwo.setMovie(movie1);
        movieSessionTwo.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 11, 1),
                        LocalTime.of(20, 30)));
        movieSessionService.add(movieSessionTwo);
        MovieSession movieSessionThree = new MovieSession();
        movieSessionThree.setCinemaHall(cinemaHallThree);
        movieSessionThree.setMovie(movie1);
        movieSessionThree.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 11, 6),
                        LocalTime.of(19, 30)));
        movieSessionService.add(movieSessionThree);

        movieSessionService.findAvailableSessions(movie1.getId(), LocalDate.now())
                .forEach(System.out::println);

        byte[] salt = HashUtil.getSalt();
        User user = new User();
        user.setEmail("user@google.com");
        user.setSalt(salt);
        user.setPassword(HashUtil.getPasswordDigest("1234", salt));
        userService.add(user);
        System.out.println(userService.findByEmail("user@google.com"));
    }
}
